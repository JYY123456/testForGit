package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DesensitiveMethod;

public interface DesensitiveMethodMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DesensitiveMethod record);

    int insertSelective(DesensitiveMethod record);

    DesensitiveMethod selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DesensitiveMethod record);

    int updateByPrimaryKey(DesensitiveMethod record);
}