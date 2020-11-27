package com.mort.middleWare.configCenter.client.init;

import com.mort.middleWare.configCenter.client.request.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author mort
 * @Description
 * @date 2020/11/27
 **/
public class ClientContainerInit extends AbstractClientInit {


    private Map<String, Client> map = new HashMap<>();


    public void init(){
        /**
         * @see META-INF/services/config.client.init
         */
        ServiceLoader<Client> loader = ServiceLoader.load(Client.class);
        for (Client client : loader) {
            map.put(client.getClass().getName(),client);
        }
    }

    //非spring方式 通过静态调用初始化
    @Override
    public void clientStart() {

    }
}
