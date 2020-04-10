package com.gx.dataI.common.enums;

public enum OperateStatus {
    SUCCESS(1,"成功")
    ,FAILED(0,"失败")
    ,ERROR(-1,"异常")
    ;
    private int code;
    private String desc;
    OperateStatus(int code,String desc){
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
