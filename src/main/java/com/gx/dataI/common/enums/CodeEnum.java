package com.gx.dataI.common.enums;

public enum CodeEnum {
    SUCCESS("200"), UNAUTH("403"), UNLOGIN("998"), LOGOUT("999"),

    INDEX("index"), LOGIN_EXCEPTION("500"), NO_NULL("no_null"),

    EXIST("1"), NOT_EXIST("0");

    private String code;

    private CodeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
