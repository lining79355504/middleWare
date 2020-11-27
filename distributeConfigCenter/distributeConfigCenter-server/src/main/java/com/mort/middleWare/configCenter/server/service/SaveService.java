package com.mort.middleWare.configCenter.server.service;

import com.mort.middleWare.configCenter.server.dto.ConfigParamsDto;

/**
 * @author mort
 * @Description
 * @date 2020/11/24
 * 多种存储的保存实现  对接用户使用配置界面
 **/
public interface SaveService {

    boolean save(ConfigParamsDto param);

}
