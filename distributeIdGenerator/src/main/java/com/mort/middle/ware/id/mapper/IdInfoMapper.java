package com.mort.middle.ware.id.mapper;

import com.mort.middle.ware.id.model.IdInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IdInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IdInfo record);

    int insertOrUpdate(IdInfo record);

    int insertOrUpdateSelective(IdInfo record);

    int insertSelective(IdInfo record);

    IdInfo selectByPrimaryKey(Integer id);

    IdInfo selectByBiz(@Param("biz")String biz);

    int updateByPrimaryKeySelective(IdInfo record);

    int updateByPrimaryKey(IdInfo record);

    int updateBatch(List<IdInfo> list);

    int updateBatchSelective(List<IdInfo> list);

    int updateByBizAndForm(@Param("updated")IdInfo updated,@Param("biz")String biz,@Param("form")Long form);

    int batchInsert(@Param("list") List<IdInfo> list);


}