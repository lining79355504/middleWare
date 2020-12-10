package com.mort.middleWare.configCenter.server.service;

import com.mort.middleWare.configCenter.server.config.ConfigManager;
import com.mort.middleWare.configCenter.server.entity.ConfigCenter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author mort
 * @Description
 * @date 2020/12/6
 **/
@Component
public class StoreRefreshService implements  InitializingBean {


    private static final int BLOCK_QUEUE_SIZE = 5000;

    @Autowired
    private List<StoreService> storeServices;

    @Resource
    private ConfigManager configManager;

    private volatile Map<String, BlockingQueue<List<ConfigCenter>>> queueMap = new ConcurrentHashMap<>();

    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);


    public BlockingQueue<List<ConfigCenter>> getRefresh() {
        return queueMap.get(configManager.getStoreType());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        scheduledExecutorService.scheduleAtFixedRate(this::execute,10, ConfigManager.getRefreshTime(), TimeUnit.MILLISECONDS);
    }

    private void execute() {
        for (StoreService storeService : storeServices) {
            queueMap.putIfAbsent(storeService.getType(), new ArrayBlockingQueue<List<ConfigCenter>>(BLOCK_QUEUE_SIZE));
            queueMap.get(storeService.getType()).add(storeService.refresh());
        }
    }


}
