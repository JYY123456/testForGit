package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DataSensitiveRule;

public interface DataSensitiveRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataSensitiveRule record);

    int insertSelective(DataSensitiveRule record);

    DataSensitiveRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataSensitiveRule record);

    int updateByPrimaryKeyWithBLOBs(DataSensitiveRule record);

    int updateByPrimaryKey(DataSensitiveRule record);
}