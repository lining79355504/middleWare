package com.mort.middleWare.configCenter.server.config;

import com.mort.middle.ware.common.utils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author mort
 * @Description
 * @date 2020/12/6
 **/
@Component
public class ConfigManager implements InitializingBean {

     private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);

    private volatile  static int REFRESH_TIME = 10;

    private volatile String STORE_TYPE ;

    @Override
    public void afterPropertiesSet() throws Exception {
        STORE_TYPE = PropertyUtils.get("classPath:application.properties", "server.store.type");
    }

    public static int getRefreshTime() {
        return REFRESH_TIME;
    }

    public void setRefreshTime(int refreshTime) {
        REFRESH_TIME = refreshTime;
    }

    public String getStoreType() {
        return STORE_TYPE;
    }

    public void setStoreType(String StoreType) {
        this.STORE_TYPE = STORE_TYPE;
    }

    @Override
    public String toString() {
        return "ConfigManager{" +
                "STORE_TYPE='" + STORE_TYPE + '\'' +
                '}';
    }
}
