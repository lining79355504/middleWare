package com.mort.middle.ware.common.utils;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpRequestUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static final String CHARSET = "UTF-8";


    public static String doGet(Map<String, String> params, String url, Header header) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            CloseableHttpResponse response;
            URIBuilder uriBuilder = new URIBuilder(url);
            List<NameValuePair> list = new LinkedList<>();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    list.add(new BasicNameValuePair(entry.getKey(), value));
                }
                uriBuilder.setParameters(list);
            }
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000)
                    .setConnectTimeout(3000).build();
            httpGet.setConfig(requestConfig);

            if (null != header) {
                httpGet.setHeader(header);
            }
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, CHARSET);
        } catch (Exception e) {
            logger.error("error ", e);
        }
        return null;
    }

    public static String doPost(Map<String, String> params, String body, String url, Header header) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            CloseableHttpResponse response;
            URIBuilder uriBuilder = new URIBuilder(url);
            List<NameValuePair> list = new LinkedList<>();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    list.add(new BasicNameValuePair(entry.getKey(), value));
                }
                uriBuilder.setParameters(list);
            }

            HttpPost httpPost = new HttpPost(uriBuilder.build());

            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000)
                    .setConnectTimeout(3000).build();
            httpPost.setConfig(requestConfig);

            if (null != header) {
                httpPost.setHeader(header);
            }
            if (!StringUtils.isEmpty(body)) {
                httpPost.setEntity(new StringEntity(body, CHARSET));
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity, CHARSET);
        } catch (Exception e) {
            logger.error("error ", e);
        }
        return null;
    }

}
