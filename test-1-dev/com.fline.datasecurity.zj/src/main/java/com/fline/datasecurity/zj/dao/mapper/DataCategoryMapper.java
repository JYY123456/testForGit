package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DataCategory;

public interface DataCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataCategory record);

    int insertSelective(DataCategory record);

    DataCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataCategory record);

    int updateByPrimaryKeyWithBLOBs(DataCategory record);

    int updateByPrimaryKey(DataCategory record);
}