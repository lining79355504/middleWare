package com.mort.middleWare.configCenter.server.entity;

import java.util.Date;

/**
 * @author mort
 * @Description
 * @date 2020/11/24
 **/
public class ConfigEnter {
    private Integer id;

    /**
     * 应用
     */
    private String app;

    /**
     * config key
     */
    private String key;

    /**
     * config 值
     */
    private String value;

    /**
     * value类型 list string json boolean
     */
    private String valuetype;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValuetype() {
        return valuetype;
    }

    public void setValuetype(String valuetype) {
        this.valuetype = valuetype;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}