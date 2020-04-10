package com.gx.dataI.common.aop;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gx.dataI.api.es.entity.LogDA;
import com.gx.dataI.api.es.entity.User;
import com.gx.dataI.api.es.repository.LogDARepository;
import com.gx.dataI.api.es.repository.LogRepository;
import com.gx.dataI.api.es.repository.UserRepository;
import com.gx.dataI.common.annotation.Log;
import com.gx.dataI.common.enums.OperateSYS;
import com.gx.dataI.common.enums.OperateStatus;
import com.gx.dataI.common.utils.JSONUtils;
import com.gx.dataI.common.utils.SessionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.gx.dataI.common.annotation.Log)")
    public void logPointCut() {
    }

    private com.gx.dataI.api.es.entity.Log log;
    private LogDA logDA;

    @Autowired
    UserRepository userRepository;
    @Autowired
    LogRepository logRepository;
    @Autowired
    LogDARepository logDARepository;

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log syslog = method.getAnnotation(Log.class);
        // 执行方法
        Object result = joinPoint.proceed();
        if (syslog!=null){
            // 请求的方法名
            String className = signature.getDeclaringTypeName();
            String methodName = method.getName();
            // 请求的参数
            Object[] args = joinPoint.getArgs();
            String parms = null;
            try {
                if (args!=null){
                    JSONArray jsonArray = new JSONArray();
                    for(Object arg:args){
                        if(arg!=null){
                            if(arg instanceof MultipartFile){
                                //因为上传的时候会出现上传文件的操作
                                MultipartFile file = (MultipartFile)arg;
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("fileName",file.getOriginalFilename());
                                jsonObject.put("name",file.getName());
                                jsonObject.put("size",file.getSize());
                                jsonObject.put("contentType",file.getContentType());
                                jsonArray.add(jsonObject);
                            }else{
                                jsonArray.add(JSONObject.toJSONString(arg));
                            }
                        }
                    }
                    parms = jsonArray.toJSONString();
                    parms = parms.substring(0, (parms.length()>4999?4999:parms.length()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            if(syslog.operateSYS()== OperateSYS.DA){
                //如果是档案的操作就写档案的日志
                logDA = new LogDA();
                logDA.setGuid(UUID.randomUUID().toString());
                logDA.setOperation(syslog.operate().getCode());
                logDA.setOperateTime(new Date());
                logDA.setMsg(syslog.msg());
                logDA.setIp(SessionUtils.getIp());

                logDA.setMethod(className + "." + methodName + "()");

                if (parms!=null){
                    logDA.setParams(parms);
                }
                // 执行时长(毫秒)
                logDA.setTime(System.currentTimeMillis() - beginTime);
            }else{
                log = new com.gx.dataI.api.es.entity.Log();
                log.setGuid(UUID.randomUUID().toString());
                log.setOperation(syslog.operate().getCode());
                if(SessionUtils.getUserName()!=null){
                    User currUser = SessionUtils.getUser();
                    if(currUser!=null){
                        log.setUserName(currUser.getUserName());
                        log.setUserGuid(currUser.getGuid());
                    }
                }
                log.setOperateTime(new Date());
                log.setMsg(syslog.msg());
                log.setIp(SessionUtils.getIp());
                log.setMethod(className + "." + methodName + "()");
                if (parms!=null){
                    log.setParams(parms);
                }
                // 执行时长(毫秒)
                log.setTime(System.currentTimeMillis() - beginTime);
            }
        }
        return result;
    }
    @AfterReturning("logPointCut()")
    public void AfterReturning(){
        if (log!=null){
            log.setStatus(OperateStatus.SUCCESS.getCode());
            logRepository.save(log);
        }
        if(logDA!=null){
            logDA.setStatus(OperateStatus.SUCCESS.getCode());
            logDARepository.save(logDA);
        }
    }

    @AfterThrowing("logPointCut()")
    public void AfterThrowing(){
        if (log!=null){
            log.setStatus(OperateStatus.FAILED.getCode());
            logRepository.save(log);
        }
        if(logDA!=null){
            logDA.setStatus(OperateStatus.FAILED.getCode());
            logDARepository.save(logDA);
        }
    }
}
