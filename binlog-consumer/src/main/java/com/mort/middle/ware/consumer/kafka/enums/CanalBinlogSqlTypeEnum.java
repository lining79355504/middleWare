package com.mort.middle.ware.consumer.kafka.enums;

/**
 * @Refreence   https://github.com/alibaba/canal/blob/master/protocol/src/main/java/com/alibaba/otter/canal/protocol/CanalEntry.java#L197
 */
public enum CanalBinlogSqlTypeEnum {

    ALTER("ALTER"),
    QUERY("QUERY"),
    ERASE("ERASE"),
    TRUNCATE("TRUNCATE"),
    RENAME("RENAME"),
    CINDEX("CINDEX"),
    DINDEX("DINDEX"),
    GTID("GTID"),
    XACOMMIT("XACOMMIT"),
    XAROLLBACK("XAROLLBACK"),
    MHEARTBEAT("MHEARTBEAT"),
    CREATE("CREATE"),
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
