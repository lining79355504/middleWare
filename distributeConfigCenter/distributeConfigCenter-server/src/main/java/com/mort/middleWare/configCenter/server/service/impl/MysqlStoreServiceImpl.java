package com.mort.middleWare.configCenter.server.service.impl;

import com.mort.middleWare.configCenter.server.dto.ConfigParamsDto;
import com.mort.middleWare.configCenter.server.entity.ConfigCenter;
import com.mort.middleWare.configCenter.server.mapper.ConfigEnterMapper;
import com.mort.middleWare.configCenter.server.service.StoreService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author mort
 * @Description
 * @date 2020/11/24
 **/
@Component
public class MysqlStoreServiceImpl implements StoreService {

    @Resource
    private ConfigEnterMapper configEnterMapper;

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
        return "mysql";
    }

    @Override
    public String getAll(String appkey) {
        return null;
    }


}
