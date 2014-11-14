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
import com.beyondsoft.framework.util.PasswordUtil;
import com.beyondsoft.thrift.rpc.DataGridRPC;
import com.beyondsoft.thrift.rpc.PageFormRPC;
import com.beyondsoft.thrift.rpc.TreeDTORPC;
import com.beyondsoft.thrift.rpc.sysuser.SysUser;
import com.beyondsoft.thrift.rpc.sysuser.SysUserService.Iface;
import com.beyondsoft.thrift.server.mapper.SysUserMapper;
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
@Service("sysUserServiceImpl")
@ThriftService(service = "com.beyondsoft.thrift.rpc.sysuser.SysUserService")
public class SysUserServiceImpl extends CommonServiceImpl implements Iface {

	@SuppressWarnings("rawtypes")
	@Resource(name = "sysUserMapper")
	private SysUserMapper sysUserMapper;
	private Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

	@Override
	public Map<String, String> checkUserLogin(String username, String password)
			throws TException {
		// TODO Auto-generated method stub

		Map<String, String> map = super.getMap();
		SysUser u = checkUserExits(username, password);
		if (u != null) {
			String status = u.getStatus();
			if (StringUtils.isNotEmpty(status) && "disabled".equals(status)) {
				map.put("key", "500");
				map.put("value", "此用户被管理员禁用！");
				return map;
			}
		} else {

			map.put("key", "500");
			map.put("value", "用户名或密码错误!");
			return map;
		}

		return map;
	}

	private SysUser checkUserExits(String username, String password) {

		String passWord = PasswordUtil.encrypt(username, password,
				PasswordUtil.getStaticSalt());

		Map<String, String> params = Maps.newHashMap();
		params.put("username", username);
		params.put("password", passWord);

		List<SysUser> listSysUser = sysUserMapper.selectByMap(params);
		if (listSysUser != null && listSysUser.size() > 0) {
			return listSysUser.get(0);
		}
		return null;
	}

	private List<TreeDTORPC> allTreeNodeSync(List<PageFormRPC> listMap) {

		List<TreeDTORPC> nl = Lists.newArrayList();

		Iterator<PageFormRPC> iter = listMap.iterator();
		while (iter.hasNext()) {
			PageFormRPC PageFormRPC = iter.next();
			TreeDTORPC tree = new TreeDTORPC();

			String text = PageFormRPC.getValue2();
			String id = PageFormRPC.getValue1();
			String pid = PageFormRPC.getValue4();
			String url = PageFormRPC.getValue3();
			String iconCls = PageFormRPC.getValue6();
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
	public boolean addUser(SysUser sysUser) throws TException {

		try {
			int count = sysUserMapper.insert(sysUser);
			return true;
		} catch (Exception e) {
			logger.info("添加用户异常:"+e.getMessage());
			throw new RuntimeException("添加用户异常");
		}

	}

	@Override
	public boolean delUser(String ids) throws TException {
		String id[] = null;
		try {
			if (StringUtils.isNotEmpty(ids)) {
				id = ids.split(",");
				for (String primaryKey : id) {
					int count = sysUserMapper.deleteByPrimaryKey(primaryKey);

				}
			}
		} catch (Exception e) {
			logger.info("删除用户异常:"+e.getMessage());
			throw new RuntimeException("删除用户异常");
		}

		return true;
	}

	@Override
	public boolean editUser(SysUser sysUser) throws TException {
		// TODO Auto-generated method stub

		try {
			int count = sysUserMapper.updateByPrimaryKey(sysUser);
			return true;
		} catch (Exception e) {
			logger.info("编辑用户异常:"+e.getMessage());
			throw new RuntimeException("编辑用户异常");
		}

	}

	@Override
	public List<TreeDTORPC> findModuleBySelf(SysUser sysUser) throws TException {

		Map<String, String> params = Maps.newHashMap();
		params.put("id", "1");// 使用缓存框架取出user
		List<PageFormRPC> list = sysUserMapper.selectModule(params);
		List<TreeDTORPC> listTreeDTORPC = allTreeNodeSync(list);

		return listTreeDTORPC;
	}

	@Override
	public DataGridRPC findUser(PageFormRPC pageFormRPC, SysUser sysUser)
			throws TException {

		DataGridRPC dg = new DataGridRPC();

		int rows = pageFormRPC.getRows();
		int page = (pageFormRPC.getPage() - 1) * rows;

		Map<String, Object> params = Maps.newHashMap();
		params.put("realname",
				sysUser.getRealname() == null ? "" : sysUser.getRealname());
		params.put("page", page);
		params.put("rows", rows);
		List<PageFormRPC> list = sysUserMapper.selectUserByName(params);
		dg.setRows(list);
		dg.setTotal(sysUserMapper.selectUserByNameCnt());
		return dg;
	}

	@Override
	public SysUser findUserById(String id) throws TException {

		return (SysUser) sysUserMapper.selectByPrimaryKey(id);
	}

}
