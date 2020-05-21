package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DataSource;

public interface DataSourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataSource record);

    int insertSelective(DataSource record);

    DataSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataSource record);

    int updateByPrimaryKeyWithBLOBs(DataSource record);

    int updateByPrimaryKey(DataSource record);
}