package com.mort.middle.ware.common;

import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

public abstract class ConfigureCenterService implements InitializingBean {

    private static final String UAT_CONFIG_URL = "127.0.0.1";

    private static final String ONLINE_CONFIG_URL = "127.0.0.1";

    private static final String  UAT_TOKEN = null ;

    private static final String  ONLINE_TOKEN = null ;

    private static final String  PRE_TOKEN = null ;

    private static String REQUEST_URL = null;

    private static String  TOKEN = null ;

    private boolean INITED ;

    // appkey , fileName ,
    private Map<String, Map<String, String>> config = new HashMap<>();

    public abstract String getUatToken();

    public abstract String getPreToken();

    public abstract String getOnLineToken();


    public ConfigureCenterService() {
        init();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Deprecated
    private String getConfig(String app, String token, String file, String zone) {
        return null;
    }

    private void init() {
        String deployEnv = getEnv();
        if ("uat".equals(deployEnv) || "dev".equals(deployEnv)) {
            REQUEST_URL = UAT_CONFIG_URL;
            TOKEN = getUatToken();
        } else {
            REQUEST_URL = ONLINE_CONFIG_URL;
            if ("pre".equals(deployEnv)) {
                TOKEN = getPreToken();
            } else {
                TOKEN = getOnLineToken();
            }
        }
        INITED = true;
    }

    public static String getEnv() {
        String env = System.getProperty("deploy_env");
        if (null != env) {
            return env;
        } else {
            return System.getenv("DEPLOY_ENV");
        }
    }

    public Map<String, Map<String, String>> getConfig() {
        return config;
    }

    public boolean isInit() {
        return INITED;
    }

    public static String getRequestUrl() {
        return REQUEST_URL;
    }

    public static String getTOKEN() {
        return TOKEN;
    }
}
