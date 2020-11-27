package com.mort.middleWare.configCenter.client.init;

import com.mort.middleWare.configCenter.client.request.Client;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mort
 * @Description
 * @date 2020/11/26
 **/
public class SpringClientContainerInit extends AbstractClientInit implements InitializingBean , ApplicationContextAware {


    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //根据接口类型返回相应的所有bean
         map = applicationContext.getBeansOfType(Client.class);
    }

    public Map<String, Client> getMap() {
        return map;
    }



    @Override
    void clientStart() {

    }
}
