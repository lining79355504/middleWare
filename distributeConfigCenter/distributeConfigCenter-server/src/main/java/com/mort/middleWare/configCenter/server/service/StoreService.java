package com.mort.middleWare.configCenter.server.service;

import com.mort.middleWare.configCenter.server.dto.ConfigParamsDto;
import com.mort.middleWare.configCenter.server.entity.ConfigCenter;

import java.util.List;

/**
 * @author mort
 * @Description
 * @date 2020/11/24
 * 多种存储的保存实现  对接用户使用配置界面
 *
 * 根据配置按时间频率刷新修改后的数据到queue
 *
 **/
public interface StoreService {

    boolean save(ConfigParamsDto param);

    List<ConfigCenter> refresh();

    String getType();

    String getAll(String appkey);

}
