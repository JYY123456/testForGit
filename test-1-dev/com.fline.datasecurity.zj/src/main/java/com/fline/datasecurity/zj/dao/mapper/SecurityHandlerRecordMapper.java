package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.SecurityHandlerRecord;

public interface SecurityHandlerRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecurityHandlerRecord record);

    int insertSelective(SecurityHandlerRecord record);

    SecurityHandlerRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecurityHandlerRecord record);

    int updateByPrimaryKey(SecurityHandlerRecord record);
}