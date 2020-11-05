package com.mort.middle.ware.consumer.kafka.enums;

public enum CanalBinlogSqlTypeEnum {

    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String typeName;

    CanalBinlogSqlTypeEnum(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
