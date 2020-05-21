package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DesensitiveTask;

public interface DesensitiveTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DesensitiveTask record);

    int insertSelective(DesensitiveTask record);

    DesensitiveTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DesensitiveTask record);

    int updateByPrimaryKey(DesensitiveTask record);
}