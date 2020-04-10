package com.gx.dataI.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.gx.dataI.api.es.entity.User;
import com.gx.dataI.api.model.SysUser;
import com.gx.dataI.api.service.ILoginService;
import com.gx.dataI.api.service.ISysUserService;
import com.gx.dataI.common.annotation.Log;
import com.gx.dataI.common.enums.OperateType;
import com.gx.dataI.common.enums.RequestConst;
import com.gx.dataI.common.utils.R;
import com.gx.dataI.common.utils.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "登录接口",description = "用于登录")
@Controller
public class LoginController {

//    @Autowired
//    ISysUserService service;
    @Autowired
    ILoginService service;

    @Value("${login.session.timeout}")
    private Integer loginSessionTimeout;

    @Log(operate = OperateType.LOGIN,msg = "登录系统")
    @ResponseBody
    @RequestMapping(value = "login",method = {RequestMethod.POST})
    @ApiOperation(value = " 登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "jsonData",value = "get请求传的参数接收字符串，post请求也可以通过url的方式传",dataType = "string"),
            @ApiImplicitParam(paramType = "body",name = "parms",value = "post请求传的参数接收的是JSON对象，不能通过get请求传",dataType = "string")})
    public R login(@RequestParam(required = false) String jsonData, @RequestBody(required = false) JSONObject parms){
        if(jsonData!=null&&parms==null){
            parms = JSONObject.parseObject(jsonData);
        }
        if (parms==null){
            return new R().parmsNullErr();
        }
        String loginId = parms.getString(RequestConst.NAME.getVal());
        String password = parms.getString(RequestConst.PASS.getVal());
//        SysUser sysUser = service.checkUser(loginId,password);
        User sysUser = service.checkUser(loginId,password);
        if(sysUser!=null){
            SessionUtils.setSessionTimeout(loginSessionTimeout);
            return new R().ok().push("token",SessionUtils.setToken(sysUser));
        }else{
            return new R().err();
        }
    }
}
