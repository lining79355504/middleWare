package com.mort.middle.ware.consumer.kafka.config;

import com.mort.middle.ware.common.ConfigureCenterService;
import com.mort.middle.ware.common.utils.HttpRequestUtil;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class BibleConfigureCenterService extends ConfigureCenterService {

    private static final String UAT_TOKEN = "XXll";

    private static final String ONLINE_TOKEN = "XXll";

    private static final String PRE_TOKEN = "XXll";

    private static final Logger logger = LoggerFactory.getLogger(BibleConfigureCenterService.class);


    public String getBibleConfig(String file, String key) {
        Map<String, String> params = new HashMap<>();
        params.put("app_id", "sycpb.public-service.bible");
        params.put("env", getEnv());
        params.put("zone", "sh001");
        params.put("build_name", "v1");
        params.put("filename", file);
        Properties prop = new Properties();
        String result = HttpRequestUtil.doGet(params, getRequestUrl(), buildBibleHeader());
        InputStream inputStream = new ByteArrayInputStream(result.getBytes());
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            logger.error("unknown config ", e);
        }
        return prop.getProperty(key);
    }


    public static String getEnv() {
        return System.getProperty("deploy_env");
    }


    private static Header buildBibleHeader() {
        return new BasicHeader("Authorization", "Token " + getTOKEN());
    }

    @Override
    public String getUatToken() {
        return UAT_TOKEN;
    }

    @Override
    public String getPreToken() {
        return PRE_TOKEN;
    }

    @Override
    public String getOnLineToken() {
        return ONLINE_TOKEN;
    }

}
