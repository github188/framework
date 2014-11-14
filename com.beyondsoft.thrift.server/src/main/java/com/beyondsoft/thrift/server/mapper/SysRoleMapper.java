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
import com.beyondsoft.thrift.rpc.sysmodule.SysModule;
import com.beyondsoft.thrift.rpc.sysrole.SysRole;
import com.beyondsoft.thrift.rpc.sysrole.SysRolePermission;
import com.beyondsoft.thrift.rpc.sysrole.SysUserRole;

/**
 * 类描述<br>
 * 角色的Mapper接口
 * 
 * @author Simon.Hoo
 * @version v1.0.0
 * @param <T>
 * @param <PK>
 */
@MapperScan("sysRoleMapper")
public interface SysRoleMapper<T, PK> extends BaseMapper<T, PK> {

	public int deleteRoleUser(String id);

	//删除角色权限
	public int deleteRolePermission(String id);

	public List<SysModule> selectAllModule();

	// 选角色回显树的勾
	public List<SysModule> selectTreeNode(SysRole role);

	// 插入角色用户中间表
	public boolean insertRoleUser(SysUserRole userRole);
	
	// 删除角色用户中间表
	public boolean deleteRoleUserMK(SysUserRole userRole);
	
	//取得模块,后期放到module的xml里面，注入
	public SysModule getModuleByPrimaryKey(String id);
	
	//添加角色权限
	public int addRolePermission(SysRolePermission rolePermission);
	
	public List<PageFormRPC> noAssignedUserDatagrid(Map<String,String> params);
	
	public long noAssignedUserDatagridCNT(Map<String,String> params);
	
	public List<PageFormRPC> assignedUserDatagrid(Map<String,String> params);
	
	public long assignedUserDatagridCNT(Map<String,String> params);
	
	
	

}
