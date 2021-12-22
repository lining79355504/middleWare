package com.mort.middleWare.configCenter.server.dto;

import java.io.Serializable;

/**
 * @author mort
 * @Description
 * @date 2020/11/24
 **/
public class ConfigParamsDto implements Serializable {

    private static final long serialVersionUID = 1300078250226996384L;

    private String app ;

    private String key ;

    private String value ;

    private String lastRefreshTime;

    /**
     * 上次更新的最大ID  client 以此作为更新对比 避免aba问题
     */
    private String xid;

    private String valueTypeClass;

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

    public String getValueTypeClass() {
        return valueTypeClass;
    }

    public void setValueTypeClass(String valueTypeClass) {
        this.valueTypeClass = valueTypeClass;
    }

    public String getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(String lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    @Override
    public String toString() {
        return "ConfigParamsDto{" +
                "app='" + app + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", lastRefreshTime='" + lastRefreshTime + '\'' +
                ", xid='" + xid + '\'' +
                ", valueTypeClass='" + valueTypeClass + '\'' +
                '}';
    }
}
