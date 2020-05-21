package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.SecurityHandler;

public interface SecurityHandlerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecurityHandler record);

    int insertSelective(SecurityHandler record);

    SecurityHandler selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecurityHandler record);

    int updateByPrimaryKey(SecurityHandler record);
}