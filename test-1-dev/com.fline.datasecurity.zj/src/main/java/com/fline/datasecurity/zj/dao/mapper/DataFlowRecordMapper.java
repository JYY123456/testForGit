package com.fline.datasecurity.zj.dao.mapper;

import com.fline.datasecurity.zj.dao.entity.DataFlowRecord;

public interface DataFlowRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataFlowRecord record);

    int insertSelective(DataFlowRecord record);

    DataFlowRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataFlowRecord record);

    int updateByPrimaryKey(DataFlowRecord record);
}