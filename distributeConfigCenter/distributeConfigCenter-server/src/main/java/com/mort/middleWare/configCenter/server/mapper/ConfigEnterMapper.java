package com.mort.middleWare.configCenter.server.mapper;

import com.mort.middleWare.configCenter.server.entity.ConfigEnter;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author mort
 * @Description
 * @date 2020/11/24
 **/
public interface ConfigEnterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConfigEnter record);

    int insertOrUpdate(ConfigEnter record);

    int insertOrUpdateSelective(ConfigEnter record);

    int insertSelective(ConfigEnter record);

    ConfigEnter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ConfigEnter record);

    int updateByPrimaryKey(ConfigEnter record);

    int updateBatch(List<ConfigEnter> list);

    int batchInsert(@Param("list") List<ConfigEnter> list);
}