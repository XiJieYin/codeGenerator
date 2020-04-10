package com.gx.dataI.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.gx.dataI.api.es.entity.User;
import com.gx.dataI.common.enums.RequestConst;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    private static Integer SESSION_TIMEOUT = 1800;

    public static final String USER_NAME = "userName";

    public static void setSessionTimeout(Integer sessionTimeout) {
        SESSION_TIMEOUT = sessionTimeout==null?1800:sessionTimeout;
    }

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes==null? null : requestAttributes.getRequest();
    }
    /**
     * 获取session
     * @return
     */
    public static HttpSession getSession(){
        return getRequest().getSession(false);
    }
    /**
     * 获取真实路径
     * @return
     */
    public static String getRealRootPath(){
        return getRequest().getServletContext().getRealPath("/");
    }
    /**
     * 获取ip
     * @return
     */
    public static String getIp() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes!=null){
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return getIpAddress(request);
        }
        return null;
    }

    /**
     * 从request中获取请求方IP
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取session中的Attribute
     * @param name
     * @return
     */
    public static Object getSessionAttribute(String name){
        HttpServletRequest request = getRequest();
        return request == null?null:request.getSession().getAttribute(name);
    }
    /**
     * 设置session的Attribute
     * @param name
     * @param value
     */
    public static void setSessionAttribute(String name,Object value){
        HttpServletRequest request = getRequest();
        if(request!=null){
            request.getSession().setAttribute(name, value);
        }
    }
    /**
     * 获取request中的Attribute
     * @param name
     * @return
     */
    public static Object getRequestAttribute(String name){
        HttpServletRequest request = getRequest();
        return request == null?null:request.getAttribute(name);
    }

    /**
     * 设置request的Attribute
     * @param name
     * @param value
     */
    public static void setRequestAttribute(String name,Object value){
        HttpServletRequest request = getRequest();
        if(request!=null){
            request.setAttribute(name, value);
        }
    }
    /**
     * 获取上下文path
     * @return
     */
    public static String getContextPath() {
        return getRequest().getContextPath();
    }
    /**
     * 删除session中的Attribute
     * @param name
     */
    public static void removeSessionAttribute(String name) {
        getRequest().getSession().removeAttribute(name);
    }

    /**
     * 设置token
     * @return
     */
    public static String setToken(User sysUser){
        String userName = sysUser.getUserName();
        JSONObject user = new JSONObject();
        user.put("time",System.currentTimeMillis());
        user.put(USER_NAME,userName);
        String token = SM4.getSm4Utils().encryptData_CBC(user.toJSONString());
        token = Base64.encodeBase64URLSafeString(token.getBytes());
        //之前直接用的uuid，这样不能存储用户信息
//        String token = UUID.randomUUID().toString();
        setSessionAttribute(RequestConst.TOKEN.getVal(), token);
        setSessionAttribute(userName,sysUser);
        updateLiveTime();
        return token;
    }

    /**
     * 查询 token
     * @param str
     * @return
     */
    public static boolean checkToken(String str){
        boolean flat = getToken()==null?false:getToken().equalsIgnoreCase(str);
        if (flat){
            updateLiveTime();
        }
        return flat;
    }

    /**
     * 更新session时间
     */
    public static void updateLiveTime(){
        getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
    }

    /**
     * 获取Token
     * @return
     */
    public static String getToken(){
        return getSessionAttribute(RequestConst.TOKEN.getVal())==null?null:getSessionAttribute(RequestConst.TOKEN.getVal()).toString();
    }

    /**
     * 获取用户名
     * @return
     */
    public static String getUserName(){
        String token = getToken();
        if(token!=null){
            //先转base64
            token = new String(Base64.decodeBase64(token));
            //再解密
            token = SM4.getSm4Utils().decryptData_CBC(token);
            JSONObject user = JSONObject.parseObject(token);
            return user.getString(USER_NAME);
        }
        return null;
    }

    /**
     * 获取缓存中的用户
     * @return
     */
    public static User getUser(){
        Object user = getSession().getAttribute(getUserName());
        return user==null?null: (User) user;
    }
}
