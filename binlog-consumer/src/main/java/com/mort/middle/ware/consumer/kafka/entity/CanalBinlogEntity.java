package com.mort.middle.ware.consumer.kafka.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import com.mort.middle.ware.consumer.kafka.enums.CanalBinlogSqlTypeEnum;
import com.mort.middle.ware.consumer.utils.JacksonUtils;

import java.io.Serializable;
import java.util.List;

public class CanalBinlogEntity<V> implements Serializable {

    private static final long serialVersionUID = -4507273519960819614L;

    //DCL DML 的时候为 null
    private List<V> data;

    private String test;

    private String database;

    private String table;

//    private CanalBinlogSqlTypeEnum type;
    /**
     * 参照 com.mort.middle.ware.consumer.kafka.enums.CanalBinlogSqlTypeEnum
     */
    private String type;

    private long es;

    private long ts;

    private long id;

    private boolean isDdl;

    //DCL DML 的时候为 null
    private List<V> old;

    private List<String> pkNames;

    private String sql;

    public List<V> getData() {
        return data;
    }

    public void setData(List<V> data) {
        this.data = data;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getEs() {
        return es;
    }

    public void setEs(long es) {
        this.es = es;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDdl() {
        return isDdl;
    }

    public void setDdl(boolean ddl) {
        isDdl = ddl;
    }

    public List<V> getOld() {
        return old;
    }

    public void setOld(List<V> old) {
        this.old = old;
    }

    public List<String> getPkNames() {
        return pkNames;
    }

    public void setPkNames(List<String> pkNames) {
        this.pkNames = pkNames;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "CanalBinlogEntity{" +
                "data=" + data +
                ", test='" + test + '\'' +
                ", database='" + database + '\'' +
                ", table='" + table + '\'' +
                ", type='" + type + '\'' +
                ", es=" + es +
                ", ts=" + ts +
                ", id=" + id +
                ", isDdl=" + isDdl +
                ", old=" + old +
                ", pkNames=" + pkNames +
                ", sql='" + sql + '\'' +
                '}';
    }

    public static void main(String[] args) {
        String str = "{\n" +
                "\t\"data\": null,\n" +
                "\t\"database\": \"test\",\n" +
                "\t\"es\": 1616770359000,\n" +
                "\t\"id\": 5,\n" +
                "\t\"isDdl\": false,\n" +
                "\t\"mysqlType\": null,\n" +
                "\t\"old\": null,\n" +
                "\t\"pkNames\": null,\n" +
                "\t\"sql\": \"ALTER TABLE `test` ADD `qw` INT\\n NULL\\n DEFAULT NULL\\n AFTER `name`\",\n" +
                "\t\"sqlType\": null,\n" +
                "\t\"table\": \"test\",\n" +
                "\t\"ts\": 1616770360136,\n" +
                "\t\"type\": \"ALTER\"\n" +
                "}";
        JacksonUtils.deserialize(str, new TypeReference<CanalBinlogEntity<String>>(){});
    }
}
