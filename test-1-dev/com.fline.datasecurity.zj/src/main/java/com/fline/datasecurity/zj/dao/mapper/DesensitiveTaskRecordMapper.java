package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DesensitiveTaskRecord;

public interface DesensitiveTaskRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DesensitiveTaskRecord record);

    int insertSelective(DesensitiveTaskRecord record);

    DesensitiveTaskRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DesensitiveTaskRecord record);

    int updateByPrimaryKey(DesensitiveTaskRecord record);
}