package com.mort.middle.ware.org.mapper;

import com.mort.middle.ware.org.model.OrgMember;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrgMember record);

    int insertOrUpdate(OrgMember record);

    int insertOrUpdateSelective(OrgMember record);

    int insertSelective(OrgMember record);

    OrgMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrgMember record);

    int updateByPrimaryKey(OrgMember record);

    int updateBatch(List<OrgMember> list);

    int batchInsert(@Param("list") List<OrgMember> list);
}