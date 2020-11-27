package com.mort.middleWare.configCenter.client;

import com.mort.middleWare.configCenter.client.config.ConfigStatus;
import com.mort.middleWare.configCenter.client.init.ClientContainerInit;
import com.mort.middleWare.configCenter.client.service.MemoryCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mort
 * @Description
 * @date 2020/11/27
 * client 对外暴露util
 **/

public class DConfig {

    private static final Logger logger = LoggerFactory.getLogger(DConfig.class);


    DConfig() {
        if (!ConfigStatus.isInited()) {
            init();
            logger.info("init done ");
        }
    }

    private synchronized void init() {
        if (!ConfigStatus.isInited()) {
            new ClientContainerInit().clientStart();
        }
    }


    public static <T> T get(String key, T defaultValue) {
        return MemoryCache.getByKeyOrDefault(key, defaultValue);
    }

}
