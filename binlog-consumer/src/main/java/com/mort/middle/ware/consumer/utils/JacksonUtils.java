package com.mort.middle.ware.consumer.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JacksonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    public final static ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String serialize(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("serialize error ", e);
        }
        return null;
    }

    public static <T> T deserialize(String string, Class<T> classOfT) {
        try {
            return MAPPER.readValue(string, classOfT);
        } catch (Exception e) {
            logger.error("deserialize error ", e);
        }
        return null;
    }

    public static <T> T deserialize(String string, TypeReference valueTypeRef) {
        try {
            return MAPPER.readValue(string, valueTypeRef);
        } catch (Exception e) {
            logger.error("deserialize error ", e);
        }
        return null;
    }
}
