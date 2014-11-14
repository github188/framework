/*
 **************************************************************************
 * 版权声明：
 * 本软件为博彥科技(深圳)有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * Mpper接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * Oct 23, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */
/*
 **************************************************************************
 * 版权声明：
 * 本软件为博彥科技(深圳)有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * Mpper接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * Oct 23, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.beyondsoft.thrift.server.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.beyondsoft.framework.mybatis.mapper.BaseMapper;
import com.beyondsoft.thrift.rpc.PageFormRPC;
import com.beyondsoft.thrift.rpc.TreeDTORPC;
import com.beyondsoft.thrift.rpc.sysmodule.SysModule;

/**
 * 类描述<br>
 * 系统用户的Mapper接口
 * 
 * @author Simon.Hoo
 * @version v1.0.0
 * @param <T>
 * @param <PK>
 */
@MapperScan("sysModuleMapper")
public interface SysModuleMapper<T, PK> extends BaseMapper<T, PK> {


	// 取树根
	public List<PageFormRPC> selectModuleRoot();
	// 取树子节点
	public List<PageFormRPC> selectModule(Map<String, String> params);

	public Long hasChildren(String pid);
	
	

}
