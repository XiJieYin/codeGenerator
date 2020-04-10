package com.gx.dataI.common.utils;

import com.alibaba.fastjson.JSONObject;

public class R extends JSONObject {

    private final String SUCCESS = "200";
    private final String ERROR = "500";

    public R(){
        this.put("code", SUCCESS);
        this.put("msg","");
    }
    public R ok(){
        return ok("操作成功！");
    }
    public R err(){
        return err("操作失败！");
    }
    public R ok(String msg){
        return result(SUCCESS,msg);
    }
    public R err(String msg){
        return result(ERROR,msg);
    }
    public R parmsNullErr(){
        return result(ERROR,"请求参数不能为空！");
    }
    public R tokenNullErr(){
        return result(ERROR,"您没有权限访问，缺少\"token\"！");
    }
    public R token403Err(){
        return result("403","您没有权限访问,Token验证失败！");
    }
    public R result(String code,String msg){
        this.put("code", code);
        this.put("msg",msg);
        return this;
    }

    public static void main(String[] args) {
        System.out.println(new R().push("w","你"));
    }

    public R push(String key,Object val){
        this.put(key,val);
        return this;
    }
}