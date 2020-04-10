package com.gx.dataI.common.enums;

public enum DataSourceEnum {
    //数据库表空间名，有需求可以再加
    DB1("db1"),DB2("db2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
