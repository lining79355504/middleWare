package com.mort.middleWare.configCenter.client.service;

import com.mort.middle.ware.common.utils.JacksonUtils;
import com.mort.middleWare.configCenter.client.config.ConfigStatus;
import com.mort.middleWare.configCenter.client.entity.MemoryCacheEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryCache {

    private static final Logger logger = LoggerFactory.getLogger(MemoryCache.class);

    private List<MemoryCacheEntity> cache = new CopyOnWriteArrayList<>();

    // app , field , value
    private static Map<String, Map<String, MemoryCacheEntity>> cacheMap = new ConcurrentHashMap<>();


    public List<MemoryCacheEntity> getCache() {
        return cache;
    }

    public Map<String, Map<String, MemoryCacheEntity>> getCacheMap() {
        return cacheMap;
    }


    public static <T> T getByKey(String key, Class<T> type) {
        MemoryCacheEntity value = getByKey(ConfigStatus.getAppKey(), key);
        return JacksonUtils.deserialize(value.getValue(), type);
    }


    public static <T> T getByKeyOrDefault(String key, T defaultValue, Class<T> type) {

        MemoryCacheEntity value = getByKey(ConfigStatus.getAppKey(), key);
        if (null == value) {
            return defaultValue;
        }
        return JacksonUtils.deserialize(value.getValue(), type);
    }

    /**
     * 配置时会记录一份类型 存到DB
     * @param key
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T getByKeyOrDefault(String key, T defaultValue) {

        MemoryCacheEntity value = getByKey(ConfigStatus.getAppKey(), key);
        if (null == value) {
            return defaultValue;
        }
        try {
            return (T) JacksonUtils.deserialize(value.getValue(), Class.forName(value.getClassName()));
        } catch (Exception e) {
            logger.info("error ", e);
        }
        return null;
    }


    private static MemoryCacheEntity getByKey(String app, String key) {
        return cacheMap.getOrDefault(app, new ConcurrentHashMap<>()).get(key);
    }

    public <T> T getByKey(String app, String key, Class<T> type) {
        MemoryCacheEntity value = cacheMap.getOrDefault(app, new ConcurrentHashMap<>()).get(key);
        if (null == value) {
            return null;
        }
        return JacksonUtils.deserialize(value.getValue(), type);
    }

}
