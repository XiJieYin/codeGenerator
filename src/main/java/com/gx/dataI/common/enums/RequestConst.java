package com.gx.dataI.common.enums;

public enum RequestConst {
    //授权token，能过Http的header增加access_token:登录返回的token
     TOKEN("access_token")
    //图片保存时文件名称，文件名称的结构为：系统类型 + “/” + 业务类型 + “/” + yyyyMMdd/文件名（带后缀名）注：系统类型如RKSYS（常口）
    ,FILENAME("fileName")
    //图片的二进制字节流
    ,FILEDATA("fileData")
    //系统类型
    ,SYSTYPE("systype")
    //业务类型（QC001、身份证 QC002、户口本...待定）·
    ,TYPE("type")
    //业务类型（C 插入 U 更新 D 删除 ）
    ,UPDATETYPE("updatetype")
    //是否同时删除图片，默认为false
    ,ISDELFILE("isdelfile")
    //子记录集
    ,SUBS("subs")
    //用户名
    ,NAME("name")
    //密码
    ,PASS("pass")
    //记录体
    ,RECORD("record")
    ;

    private String val;

    private RequestConst(String val){
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
