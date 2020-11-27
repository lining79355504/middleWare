package com.mort.middleWare.configCenter.client.init;

import com.mort.middleWare.configCenter.client.request.Client;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mort
 * @Description
 * @date 2020/11/27
 **/
public abstract class AbstractClientInit {

    protected Map<String, Client> map = new HashMap<>();

    abstract void clientStart();

}
