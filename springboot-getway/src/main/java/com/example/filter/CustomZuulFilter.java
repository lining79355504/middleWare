package com.example.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mort
 * @Description
 * @date 2020/12/3
 **/
@Component
public class CustomZuulFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomZuulFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("Test", "TestSample");

        //获取request
        HttpServletRequest request = ctx.getRequest();

        // 获取请求参数name
        String name = "";

        try {
            // 请求方法
            String method = request.getMethod();
            logger.info(String.format("%s >>> %s", method, request.getRequestURL().toString()));
            // 获取请求的输入流
            InputStream in = request.getInputStream();
            String body = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            // 如果body为空初始化为空json
            if (StringUtils.isBlank(body)) {
                body = "{}";
            }
            logger.info("body" + body);
            // 转化成json
            JSONObject json = JSONObject.parseObject(body);


            // get方法和post、put方法处理方式不同
            if ("GET".equals(method)) {

                // 获取请求参数name
                name = request.getParameter("name");

                if (name != null) {
                    // 关键步骤，一定要get一下,下面才能取到值requestQueryParams
                    request.getParameterMap();
                    Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
                    if (requestQueryParams == null) {
                        requestQueryParams = new HashMap<>();
                    }
                    List<String> arrayList = new ArrayList<>();
                    String key = "key";
//                    String aes_decodedStr = AESUtil.getInstance().decode(name, key);
                    String aes_decodedStr = "decode str";
                    arrayList.add(aes_decodedStr + "");
                    requestQueryParams.put("decodename", arrayList);
                    ctx.setRequestQueryParams(requestQueryParams);
                }
            }// post和put需重写HttpServletRequestWrapper
            else if ("POST".equals(method) || "PUT".equals(method)) {

                // 获取请求参数name
                name = json.getString("name");

                if (name != null) {

                    String key = "key";
//                String aes_encodedStr = AESUtil.getInstance().encode(name, key);
//                log.info("加密：" + aes_encodedStr);
//                json.put("decodename", aes_decodedStr);
//                    String aes_decodedStr = AESUtil.getInstance().decode(name, key);
                    String aes_decodedStr = "decode str";
                    logger.info("解密：" + aes_decodedStr);

                    // 把解密之后的参数放到json里
                    json.put("decodename", aes_decodedStr);
                    String newBody = json.toString();
                    logger.info("newBody" + newBody);
                    final byte[] reqBodyBytes = newBody.getBytes();


                    // 重写上下文的HttpServletRequestWrapper
                    ctx.setRequest(new HttpServletRequestWrapper(request) {
                        @Override
                        public ServletInputStream getInputStream() throws IOException {
                            return new ServletInputStreamWrapper(reqBodyBytes);
                        }

                        @Override
                        public int getContentLength() {
                            return reqBodyBytes.length;
                        }

                        @Override
                        public long getContentLengthLong() {
                            return reqBodyBytes.length;
                        }
                    });
                }
            }
        } catch (Exception e) {
            logger.error("error", e);
        }

        return ctx;
    }
}
