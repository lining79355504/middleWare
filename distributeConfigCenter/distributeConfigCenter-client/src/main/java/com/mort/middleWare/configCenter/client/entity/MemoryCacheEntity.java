package com.mort.middleWare.configCenter.client.entity;

import java.io.Serializable;

public class MemoryCacheEntity implements Serializable {

    private static final long serialVersionUID = 6125899281013488663L;

    private String app ;

    private String key;

    private String value;

    private String className;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getValue() {
        return value;
    }
}
