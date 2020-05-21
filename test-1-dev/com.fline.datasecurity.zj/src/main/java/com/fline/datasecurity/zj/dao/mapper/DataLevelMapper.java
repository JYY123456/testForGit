package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DataLevel;

public interface DataLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataLevel record);

    int insertSelective(DataLevel record);

    DataLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataLevel record);

    int updateByPrimaryKey(DataLevel record);
}