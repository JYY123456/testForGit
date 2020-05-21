package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DataSensitive;

public interface AataSensitiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataSensitive record);

    int insertSelective(DataSensitive record);

    DataSensitive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataSensitive record);

    int updateByPrimaryKey(DataSensitive record);
}