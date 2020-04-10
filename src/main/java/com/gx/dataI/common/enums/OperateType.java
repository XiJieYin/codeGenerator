package com.gx.dataI.common.enums;

public enum OperateType {
    LOGIN(0,"登录")
    ,INSERT(1,"增")
    ,DELETE(2,"删")
    ,MODIFY(3,"改")
    ,QUERY(4,"查")
    ,UPLOAD(5,"上传文件")
    ,SAVE(6,"保存数据")
    ,ACCESS(7,"URL访问文件")
    ,DOWNLOAD(8,"下载文件")
    ,LASTING(9,"归档")
    ;

    private int code;
    private String desc;

    OperateType(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
