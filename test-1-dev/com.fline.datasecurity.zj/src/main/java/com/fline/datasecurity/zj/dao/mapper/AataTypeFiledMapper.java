package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DataTypeFiled;

public interface AataTypeFiledMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataTypeFiled record);

    int insertSelective(DataTypeFiled record);

    DataTypeFiled selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataTypeFiled record);

    int updateByPrimaryKey(DataTypeFiled record);
}