package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DesensitiveDataRecord;

public interface DesensitiveDataRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DesensitiveDataRecord record);

    int insertSelective(DesensitiveDataRecord record);

    DesensitiveDataRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DesensitiveDataRecord record);

    int updateByPrimaryKey(DesensitiveDataRecord record);
}