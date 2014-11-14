package com.beyondsoft.thrift.server.mapper;

import java.util.List;

import com.beyondsoft.framework.mybatis.mapper.BaseMapper;
import com.beyondsoft.thrift.rpc.TreeDTORPC;
import com.beyondsoft.thrift.rpc.sysdic.SysDic;



public interface SysDicMapper<T, PK> extends BaseMapper<T, PK> {
	
	
	Long hasChildren(String id);
	
	List<SysDic> getDicTree(String id);
	
    int deleteByPrimaryKey(String id);

    int insert(SysDic record);

    int insertSelective(SysDic record);

    SysDic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDic record);

    int updateByPrimaryKey(SysDic record);
}