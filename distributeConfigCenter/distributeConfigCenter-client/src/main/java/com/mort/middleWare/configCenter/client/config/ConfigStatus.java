package com.mort.middleWare.configCenter.client.config;

import com.mort.middle.ware.common.utils.PropertyUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author mort
 * @Description
 * @date 2020/11/27
 **/
public class ConfigStatus {

    private static AtomicBoolean init = new AtomicBoolean(false);

    private static String APP_KEY = null;


    static {
        APP_KEY = PropertyUtils.get("app.name", PropertyUtils.PROPERTIES_FILE);
    }

    public static void init() {
        init.set(true);
    }

    public static void destroy() {
        init.set(false);
    }

    public static boolean isInited() {
        return init.get();
    }

    public static String getAppKey() {
        return APP_KEY;
    }
}
