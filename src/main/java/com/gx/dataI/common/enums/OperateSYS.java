package com.gx.dataI.common.enums;

public enum  OperateSYS {
    DA(0,"档案")
    ,CK(1,"常口")
    ,ZX(2,"咨询")
    ;

    private int code;
    private String desc;

    OperateSYS(int code,String desc){
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
