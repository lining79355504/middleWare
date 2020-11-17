package com.mort.middle.ware.org.mapper;
import java.util.Date;

import com.mort.middle.ware.org.model.OrgGroup;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrgGroup record);

    int insertOrUpdate(OrgGroup record);

    int insertOrUpdateSelective(OrgGroup record);

    int insertSelective(OrgGroup record);

    OrgGroup selectByPrimaryKey(Integer id);

    List<OrgGroup> selectAllByCtime(@Param("ctime")Date ctime);

    List<OrgGroup> selectByAll(OrgGroup orgGroup);

    List<OrgGroup> selectAll(@Param("limit") Integer limit);

    int updateByPrimaryKeySelective(OrgGroup record);

    int updateByPrimaryKey(OrgGroup record);

    int updateBatch(List<OrgGroup> list);

    int batchInsert(@Param("list") List<OrgGroup> list);
}