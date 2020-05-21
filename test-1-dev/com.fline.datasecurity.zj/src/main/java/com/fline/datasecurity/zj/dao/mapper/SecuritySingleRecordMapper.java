package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.SecuritySingleRecord;

public interface SecuritySingleRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecuritySingleRecord record);

    int insertSelective(SecuritySingleRecord record);

    SecuritySingleRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecuritySingleRecord record);

    int updateByPrimaryKey(SecuritySingleRecord record);
}