package com.mort.middleWare.configCenter.server.service.impl;

import com.mort.middleWare.configCenter.server.dto.ConfigParamsDto;
import com.mort.middleWare.configCenter.server.entity.ConfigCenter;
import com.mort.middleWare.configCenter.server.service.StoreService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author mort
 * @Description
 * @date 2020/11/24
 **/
@Component
public class ZKStoreServiceImpl implements StoreService {

    @Override
    public boolean save(ConfigParamsDto param) {
        return false;
    }

    @Override
    public List<ConfigCenter> refresh() {
        return Collections.emptyList();
    }

    @Override
    public String getType() {
        return "zookeeper";
    }

    @Override
    public String getAll(String appkey) {
        return null;
    }
}
