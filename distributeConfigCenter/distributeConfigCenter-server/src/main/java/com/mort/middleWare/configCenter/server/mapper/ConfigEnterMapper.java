package com.mort.middleWare.configCenter.server.mapper;

import com.mort.middleWare.configCenter.server.entity.ConfigCenter;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author mort
 * @Description
 * @date 2020/11/24
 **/
public interface ConfigEnterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConfigCenter record);

    int insertOrUpdate(ConfigCenter record);

    int insertOrUpdateSelective(ConfigCenter record);

    int insertSelective(ConfigCenter record);

    ConfigCenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConfigCenter record);

    int updateByPrimaryKey(ConfigCenter record);

    int updateBatch(List<ConfigCenter> list);

    int batchInsert(@Param("list") List<ConfigCenter> list);
}