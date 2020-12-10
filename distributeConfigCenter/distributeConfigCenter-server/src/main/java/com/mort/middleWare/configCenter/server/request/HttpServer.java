package com.mort.middleWare.configCenter.server.request;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.mort.middleWare.configCenter.client.DConfig;
import com.mort.middleWare.configCenter.client.service.MemoryCache;
import com.mort.middleWare.configCenter.server.dto.ConfigParamsDto;
import com.mort.middleWare.configCenter.server.entity.ConfigCenter;
import com.mort.middleWare.configCenter.server.service.StoreRefreshService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

@Controller
@RequestMapping("/polling/")
public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    @Resource
    private StoreRefreshService storeRefreshService;


    //guava中的Multimap，多值map,对map的增强，一个key可以保持多个value
    private Multimap<String, DeferredResult<Object>> watchRequests = Multimaps.synchronizedSetMultimap(HashMultimap.create());


    /**
     * 长轮询
     *
     * @return
     */
    @RequestMapping(value = "get_config", method = RequestMethod.GET)
    public @ResponseBody
    DeferredResult<Object> getMessage(ConfigParamsDto paramsDto) {


        String value = DConfig.get("test", "asdasd");

//        String value = MemoryCache.getByKeyOrDefault("test","asdasd", String.class);

        DeferredResult<Object> deferredResult = new DeferredResult<Object>(30000L, null);  //设置超时30s,超时返回null
        BlockingQueue<List<ConfigCenter>> refreshQueue = storeRefreshService.getRefresh();

        deferredResult.onCompletion(new Runnable() {
            @Override
            public void run() {
                System.out.println("onCompletion");
                logger.info("onCompletion");
//                watchRequests.remove(paramsDto.getApp(), deferredResult);
            }
        });
//        watchRequests.put(paramsDto.getApp(), deferredResult);
        logger.info("Servlet thread released");

        deferredResult.setResult(refreshQueue.poll());
        return deferredResult;
    }


}
