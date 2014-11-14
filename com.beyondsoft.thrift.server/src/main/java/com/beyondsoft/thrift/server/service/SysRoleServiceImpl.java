/*
 **************************************************************************
 * 版权声明：
 * 本软件为博彥科技(深圳)有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * Thrift业务实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * Oct 23, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.beyondsoft.thrift.server.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.beyondsoft.framework.annotation.ThriftService;
import com.beyondsoft.framework.util.StringUtil;
import com.beyondsoft.framework.util.UuidUtils;
import com.beyondsoft.thrift.rpc.DataGridRPC;
import com.beyondsoft.thrift.rpc.PageFormRPC;
import com.beyondsoft.thrift.rpc.TreeDTORPC;
import com.beyondsoft.thrift.rpc.sysmodule.SysModule;
import com.beyondsoft.thrift.rpc.sysrole.SysRole;
import com.beyondsoft.thrift.rpc.sysrole.SysRolePermission;
import com.beyondsoft.thrift.rpc.sysrole.SysRoleService.Iface;
import com.beyondsoft.thrift.rpc.sysrole.SysUserRole;
import com.beyondsoft.thrift.server.mapper.SysRoleMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 类描述<br>
 * 系统用户业务实现
 * 
 * @author Simon.Hoo
 * @version v1.0
 */
@SuppressWarnings("unchecked")
@Service("sysRoleServiceImpl")
@ThriftService(service = "com.beyondsoft.thrift.rpc.sysrole.SysRoleService")
public class SysRoleServiceImpl extends CommonServiceImpl implements Iface {

	@SuppressWarnings("rawtypes")
	@Resource(name = "sysRoleMapper")
	private SysRoleMapper sysRoleMapper;

	private Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

	@Override
	public int addRole(SysRole role)  throws TException {
		
		try {
			int count = sysRoleMapper.insert(role);
			return count;
		} catch (Exception e) {
			logger.info("添加角色异常:"+e.getMessage());
			throw new RuntimeException("添加角色异常");
		}
		
	}

	@Override
	public int delRole(SysRole role)  throws TException {
		// 此处角色是一个一个删除的，页面是单选
		String id[] = null;
		String ids = role.getId();// 此处id，可能是多个值

		if (StringUtils.isNotEmpty(ids)) {
			try {
				id = ids.split(",");
				for (String primaryKey : id) {
					int count = sysRoleMapper.deleteByPrimaryKey(primaryKey);// 1.删除角色表

				}

				for (String roleId : id) {
					int count = sysRoleMapper.deleteRoleUser(roleId);// 2.删除角色与用户中间表

				}

				for (String roleId : id) {
					int count = sysRoleMapper.deleteRolePermission(roleId);// 2.删除角色与权限中间表

				}
			} catch (Exception e) {
				logger.info("删除角色异常:"+e.getMessage());
				throw new RuntimeException("删除角色异常");
			}

		}
		return 0;
	}

	@Override
	public int editRole(SysRole role)  throws TException {
		try {
			int count = sysRoleMapper.updateByPrimaryKey(role);
			return count;
		} catch (Exception e) {
			logger.info("编辑角色异常:"+e.getMessage());
			throw new RuntimeException("编辑角色异常");
		}
		
	}

	@Override
	public DataGridRPC findRole(PageFormRPC pageFormRPC) throws TException {

		DataGridRPC dg = new DataGridRPC();
		int rows = pageFormRPC.getRows();
		int page = (pageFormRPC.getPage() - 1) * rows;

		Map<String, Object> params = Maps.newHashMap();
		params.put("rows", rows);
		params.put("page", page);

		List<PageFormRPC> list = sysRoleMapper.selectByMap(params);
		dg.setRows(list);
		dg.setTotal(list.size());
		return dg;

	}

	@Override
	public boolean addUser(SysRole role) throws TException {
		// 1.添加tuser_role中间表
		String roleId = role.getId();
		String[] userId = StringUtil.splitString(role.getIds());

		try {
			for (String id : userId) {
				SysUserRole userRole = new SysUserRole();
				userRole.setRole_id(roleId);
				userRole.setUser_id(id);
				sysRoleMapper.insertRoleUser(userRole);
			}
		} catch (Exception e) {
			logger.info("角色分配用户异常:"+e.getMessage());
			throw new RuntimeException("角色分配用户异常");
		}
		

		return true;
	}

	@Override
	public int ifDelete(SysRole role) throws TException {

		String id = role.getId();
		SysRole sysRole = (SysRole) sysRoleMapper.selectByPrimaryKey(role);

		if (null == sysRole) {
			return 0;
		}
		if ("1".equals(sysRole.getId())) {

			return 1;
		}
		return 2;
	}

	@Override
	public List<TreeDTORPC> menuListSync() throws TException {
		// TODO Auto-generated method stub
		List<TreeDTORPC> nl = Lists.newArrayList();
		List<SysModule> list = sysRoleMapper.selectAllModule();

		Iterator<SysModule> iter = list.iterator();
		while (iter.hasNext()) {
			SysModule sysModule = iter.next();
			TreeDTORPC tree = new TreeDTORPC();

			String text = sysModule.getName();
			String id = sysModule.getId();
			String pid = sysModule.getParentId();
			String url = sysModule.getUrl();
			String iconCls = sysModule.getIconCls();
			tree.setUrl(url);
			tree.setId(id);
			tree.setPid(pid);
			tree.setText(text);
			tree.setIconCls(iconCls);
			nl.add(tree);
		}

		return nl;

	}

	@Override
	public boolean removeUser(SysRole role) throws TException {
		// 1.删除tuser_role中间表
		String roleId = role.getId();
		String[] userId = StringUtil.splitString(role.getIds());

		try {
			for (String id : userId) {

				SysUserRole userRole = new SysUserRole();
				userRole.setRole_id(roleId);
				userRole.setUser_id(id);
				sysRoleMapper.deleteRoleUserMK(userRole);

			}
		} catch (Exception e) {
			logger.info("角色移除用户异常:"+e.getMessage());
			throw new RuntimeException("角色移除用户异常");
		}
		

		return true;
	}

	@Override
	public String selectTreeNode(SysRole role) throws TException {
		// TODO Auto-generated method stub

		boolean flag = false;// flag用于判断角色下是否有已分配的模块，如果有则flag为true
								// ids返回为"2,3,4",如果没有ids返回为""
		String ids = "";
		StringBuffer strbuf = new StringBuffer();
		List<SysModule> listMap = sysRoleMapper.selectTreeNode(role);
		if (null != listMap && listMap.size() > 0) {
			flag = true;
			for (SysModule module : listMap) {
				String id = module.getId();// 取出tmodule的id
				strbuf.append(id);
				strbuf.append(",");
			}
		}
		if (flag) {
			ids = strbuf.toString();
			ids = ids.substring(0, ids.length() - 1);
		}
		return ids;
	}

	@Override
	public DataGridRPC noAssignedUserDatagrid(SysRole role,
			PageFormRPC pageformRPC) throws TException {
		// TODO Auto-generated method stub

		List<PageFormRPC> pgRpc = null;
		DataGridRPC dg = new DataGridRPC();
		String id = role.getId();
		List<Map<String, Object>> list = Lists.newArrayList();
		Long cnt = 0l;
		int rows = pageformRPC.getRows();
		int page = (pageformRPC.getPage() - 1) * rows;
		// 当删除角色时刷新用户列表为空数据时构造的数据=999
		// back\management\security\role\list.jsp
		if (StringUtils.isNotEmpty(id) && !"999".equals(id)) {

			Map<String, Object> params = Maps.newHashMap();
			params.put("id", id);
			params.put("page", page);
			params.put("rows", rows);
			pgRpc = sysRoleMapper.noAssignedUserDatagrid(params);
			cnt = sysRoleMapper.noAssignedUserDatagridCNT(params);

		}

		dg.setTotal(cnt);
		dg.setRows(pgRpc);
		return dg;

	}

	@Override
	public DataGridRPC assignedUserDatagrid(SysRole role,
			PageFormRPC pageFormRPC) throws TException {

		List<PageFormRPC> pgRpc = null;
		DataGridRPC dg = new DataGridRPC();
		String id = role.getId();
		List<Map<String, Object>> list = Lists.newArrayList();
		Long cnt = 0l;

		int rows = pageFormRPC.getRows();
		int page = (pageFormRPC.getPage() - 1) * rows;
		if (StringUtils.isNotEmpty(id) && !"999".equals(id)) {

			Map<String, Object> params = Maps.newHashMap();
			params.put("id", id);
			params.put("page", page);
			params.put("rows", rows);
			pgRpc = sysRoleMapper.assignedUserDatagrid(params);
			cnt = sysRoleMapper.assignedUserDatagridCNT(params);

		}

		dg.setTotal(cnt);
		dg.setRows(pgRpc);
		return dg;

	}

	@Override
	public boolean insertDeleteRoleModule(SysRole role) throws TException {
		// TODO Auto-generated method stub
		try {
		// 通过角色取出trole_permission中的permission，从而查到tmodule的sn取出tmodule.id集合
		String roleId = role.getId();
		sysRoleMapper.deleteRolePermission(roleId);// 2.删除角色与权限中间表

		String moduleIds = role.getIds();
		String modules[] = StringUtil.splitString(moduleIds);
		
			if (null != modules && !StringUtils.isEmpty(moduleIds)) {
				for (String m : modules) {
					SysModule module = sysRoleMapper.getModuleByPrimaryKey(m);
					String sn = module.getSn();

					if (null != sn && !"".equals(sn)) {
						SysRolePermission rp = new SysRolePermission();
						rp.setId(UuidUtils.getUUid());
						rp.setPermission(sn);
						rp.setRole_id(roleId);

						sysRoleMapper.addRolePermission(rp);

					}

				}
			}
		} catch (Exception e) {
			logger.info("角色分配菜单模块异常:"+e.getMessage());
			throw new RuntimeException("角色分配菜单模块异常");
		}
		

		return true;
	}

}
