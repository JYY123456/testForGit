package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.AbnormalFlowRule;

public interface AbnormalFlowRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AbnormalFlowRule record);

    int insertSelective(AbnormalFlowRule record);

    AbnormalFlowRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AbnormalFlowRule record);

    int updateByPrimaryKey(AbnormalFlowRule record);
}