package com.gx.dataI.common.filter;

import com.gx.dataI.common.enums.RequestConst;
import com.gx.dataI.common.utils.FastDFSUtil;
import com.gx.dataI.common.utils.SessionUtils;
import com.gx.dataI.common.utils.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.websocket.Session;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFilter implements Filter {

    //配置白名单
    protected static List<Pattern> patterns = new ArrayList<Pattern>();
    public static List<String>  allowList = new ArrayList<>();
    //静态代码块，在虚拟机加载类的时候就会加载执行，而且只执行一次
    static {
        patterns.add(Pattern.compile("/login"));
        //现在需要Token了，就是需要先登录，才能看图片
        patterns.add(Pattern.compile("/swagger-ui.html.*"));
        patterns.add(Pattern.compile("/webjars/.*"));
        patterns.add(Pattern.compile(".*swagger-resources.*"));
        patterns.add(Pattern.compile(".*v2/api-docs.*"));
        //用户管理
        patterns.add(Pattern.compile("/user.*"));
        //代码生成器
        patterns.add(Pattern.compile("/generator.*"));
        //日志管理
        patterns.add(Pattern.compile("/log/list.*"));
        patterns.add(Pattern.compile("/log/exportExcel.*"));
        //档案接口
        patterns.add(Pattern.compile("/da.*"));
        System.out.println(allowList.size());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(httpResponse);
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (isInclude(url)) {
            Pattern pattern = Pattern.compile("/swagger-ui.html.*");
            Matcher matcher = pattern.matcher(url);
            String ip = SessionUtils.getIpAddress(httpRequest);
            if(!matcher.matches()||(matcher.matches()&&isAllow(ip))){
                //在白名单中的url,放行访问
                filterChain.doFilter(httpRequest, httpResponse);
                return;
            }
        }
//        System.out.println(SessionUtils.getToken());
        if (SessionUtils.getToken() != null) {
            //若为登录状态 获取 access_token
            String token = httpRequest.getHeader(RequestConst.TOKEN.getVal());
            Enumeration<String> headers = httpRequest.getHeaderNames();
            System.out.println(headers);
            if(token!=null){
                if(SessionUtils.checkToken(token)){
                    filterChain.doFilter(httpRequest, httpResponse);
                    return;
                }else{
                    wrapper.sendError(403,"身份验证失败！");
                }
            }else{
                wrapper.sendError(403,"无Token信息！");
            }
        } else {
            //否则默认访问登录接口
            wrapper.sendError(403,"未登录！");
        }
    }

    @Override
    public void destroy() {

    }
    //判断当前请求是否在白名单
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
    private boolean isAllow(String ip){
        if(ip.equals("127.0.0.1")||ip.equalsIgnoreCase("localhost")||ip.equals("0:0:0:0:0:0:0:1")){
            return true;
        }
        System.out.println(allowList.size());
        if(allowList!=null&&allowList.size()>0){
            for (int i=0;i<allowList.size();i++){
                if(ip.equalsIgnoreCase(allowList.get(i))){
                    return true;
                }
            }
        }
        return false;
    }
}
