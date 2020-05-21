package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.SecuritySingle;

public interface SecuritySingleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecuritySingle record);

    int insertSelective(SecuritySingle record);

    SecuritySingle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecuritySingle record);

    int updateByPrimaryKey(SecuritySingle record);
}