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

    @Override
    public String toString() {
        return "ConfigParamsDto{" +
                "app='" + app + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", valueTypeClass='" + valueTypeClass + '\'' +
                '}';
    }
}
