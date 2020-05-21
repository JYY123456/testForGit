package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.WhiteList;

public interface WhiteListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WhiteList record);

    int insertSelective(WhiteList record);

    WhiteList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WhiteList record);

    int updateByPrimaryKey(WhiteList record);
}