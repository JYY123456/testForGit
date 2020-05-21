package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DesensitiveTemplate;

public interface DesensitiveTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DesensitiveTemplate record);

    int insertSelective(DesensitiveTemplate record);

    DesensitiveTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DesensitiveTemplate record);

    int updateByPrimaryKey(DesensitiveTemplate record);
}