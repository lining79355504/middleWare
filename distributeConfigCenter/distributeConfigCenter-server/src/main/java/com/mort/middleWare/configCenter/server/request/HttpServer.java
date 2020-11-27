package com.mort.middleWare.configCenter.server.request;


import com.mort.middleWare.configCenter.client.DConfig;
import com.mort.middleWare.configCenter.client.service.MemoryCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;

@Controller
@RequestMapping("/polling/")
public class HttpServer {

     private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    /**
     * 长轮询
     * @return
     */
    @RequestMapping(value="msg", method= RequestMethod.GET)
    public @ResponseBody
    DeferredResult<String> getMessage() {


        String value = DConfig.get("test","asdasd");

//        String value = MemoryCache.getByKeyOrDefault("test","asdasd", String.class);

        final String userId = "mort";
        DeferredResult<String> result = new DeferredResult<String>(30000l,null);  //设置超时30s,超时返回null
//        final Map<String, DeferredResult> resultMap= messageContainer.getUserMessages();
//        resultMap.put(userId, result);

        result.onCompletion(new Runnable()
        {
            @Override
            public void run() {
//                resultMap.remove(userId);
                System.out.println("onCompletion");
                logger.info("onCompletion");

            }
        });

        return result;
    }


}
