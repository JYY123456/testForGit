package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.AuditApply;

public interface AuditApplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuditApply record);

    int insertSelective(AuditApply record);

    AuditApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuditApply record);

    int updateByPrimaryKey(AuditApply record);
}