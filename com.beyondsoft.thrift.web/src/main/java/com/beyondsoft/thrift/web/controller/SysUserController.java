package com.beyondsoft.thrift.web.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beyondsoft.thrift.rpc.DataGridRPC;
import com.beyondsoft.thrift.rpc.PageFormRPC;
import com.beyondsoft.thrift.rpc.TreeDTORPC;
import com.beyondsoft.thrift.rpc.syslog.SysLog;
import com.beyondsoft.thrift.rpc.syslog.SysLogService;
import com.beyondsoft.thrift.rpc.sysuser.SysUser;
import com.beyondsoft.thrift.rpc.sysuser.SysUserService;
import com.beyondsoft.thrift.web.pageModel.DataGrid;
import com.beyondsoft.thrift.web.pageModel.Json;
import com.beyondsoft.thrift.web.pageModel.PageForm;
import com.beyondsoft.thrift.web.pageModel.TreeDTO;
import com.beyondsoft.thrift.web.pageModel.Tuser;
import com.beyondsoft.thrift.web.util.DateUtil;
import com.beyondsoft.thrift.web.util.Globals;
import com.beyondsoft.thrift.web.util.IpUtil;
import com.beyondsoft.thrift.web.util.PasswordUtil;
import com.beyondsoft.thrift.web.util.StringUtil;
import com.beyondsoft.thrift.web.util.UuidUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/userController")
public class SysUserController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(SysUserController.class);

	private static final String USERLIST = "security/user/list";// 用户列表页面

	private static final String yhglAdd = "security/user/yhglAdd";// 添加用户页面

	private static final String yhglEdit = "security/user/yhglEdit";// 编辑用户页面

	private static final String yhglEditRole = "security/user/yhglEditRole";// 设置用户分配角色的页面

	/**
	 * 功能: 跳转到用户列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toPage")
	public String treePage() {
		return USERLIST;
	}

	/**
	 * 功能: 跳转到用户添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddPage")
	public String toAddPage() {
		return yhglAdd;
	}

	/**
	 * 功能: 跳转到用户编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/toEditPage")
	public String toEditPage() {
		return yhglEdit;
	}

	/**
	 * 功能: 跳转到分配用户角色页面
	 * 
	 * @return
	 */
	@RequestMapping("/toModifyRolePage")
	public String toModifyRolePage() {
		return yhglEditRole;
	}

	/**
	 * 功能: 用户列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/datagrid")
	@ResponseBody
	public DataGrid datagrid(PageForm pageform, String name, SysUser sysUser) {
		List<Tuser> tuserList = Lists.newArrayList();
		PageFormRPC pg = new PageFormRPC();
		pg.setRows(pageform.getRows());
		pg.setPage(pageform.getPage());
		DataGrid dg = new DataGrid();
		sysUser.setRealname(name);
		try {
			SysUserService.Client client = (SysUserService.Client) getClient("SysUserService");
			DataGridRPC dgRpc =client.findUser(pg, sysUser);
			List<PageFormRPC> list = dgRpc.getRows();
			Iterator<PageFormRPC> iter = list.iterator();
			while (iter.hasNext()) {
			 PageFormRPC map =iter.next();
			 Tuser user = new Tuser();
			 user.setId(map.getValue1());
			 user.setCreateTime(StringUtil.subTimeString(map.getValue2()));
			 user.setStatus(map.getValue3());
			 user.setUsername(map.getValue4());
			 user.setEmail(map.getValue5());
			 user.setRealname(map.getValue6());
			 user.setPhone(map.getValue7());
			 user.setRoleName(map.getValue8());
			 user.setOrgName(map.getValue9());
		     user.setOrgId(map.getValue10());
			 user.setRoleIds(map.getValue11());
			 tuserList.add(user);
			}
			

			
			dg.setTotal(dgRpc.getTotal());
			dg.setRows(tuserList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dg;
	}

	/**
	 * 功能: 用户保存
	 * 
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Json save(SysUser sysUser) {
		Json j = getSuccessObj("");
		try {
			SysUserService.Client client = (SysUserService.Client) getClient("SysUserService");
			sysUser.setCreate_time(DateUtil.getToDayLStr());
			sysUser.setId(UuidUtils.getUUid());
			sysUser.setOrg_id("9");
            client.addUser(sysUser);
		} catch (Exception e) {
			logger.info(e.toString());
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("操作失败！");
		}
		return j;
	}

	/**
	 * 功能: 用户删除
	 * 
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Tuser user) {
		Json j = getSuccessObj("");
		List<SysUser> list =Lists.newArrayList();
		try {
			SysUserService.Client client = (SysUserService.Client) getClient("SysUserService");
			boolean flag=client.delUser(user.getIds());
		} catch (Exception e) {
			logger.info(e.toString());
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("操作失败！");
		}
		return j;
	}

	/**
	 * 功能: 用户编辑
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(SysUser sysUser) {
		Json j = getSuccessObj("");
		try {
			SysUserService.Client client = (SysUserService.Client) getClient("SysUserService");
			
			sysUser.setOrg_id("9");
			sysUser.setCreate_time(DateUtil.getToDayLStr());
			String password = PasswordUtil.encrypt(sysUser.getUsername(),
					sysUser.getPassword(), PasswordUtil.getStaticSalt());
			sysUser.setPassword(password);
			boolean flag =client.editUser(sysUser);
			
		} catch (Exception e) {
			logger.info(e.toString());
			e.printStackTrace();
			j.setSuccess(false);
			j.setMsg("操作失败！");
		}
		return j;
	}
	 /**
     * 功能: 快速改变用户的启用与禁用状态
     * 
     * @return
     */
    @RequestMapping("/editState")
    @ResponseBody
    public Json editState(SysUser sysUser) {

        Json j = getSuccessObj("");
        try {
			SysUserService.Client client = (SysUserService.Client) getClient("SysUserService");
			SysUser sysUser2=client.findUserById(sysUser.getId());//重数据库取得的值
            String status = sysUser2.getStatus();
            String statusChange = "enabled".equals(status) ? "disabled" : "enabled";
            sysUser.setStatus(statusChange);
            boolean flag =client.editUser(sysUser);
        } catch (Exception e) {
            logger.info(e.toString());
            e.printStackTrace();
            j.setSuccess(false);
            j.setMsg("操作失败！");
        }
        return j;
    }
	/**
	 * 功能：登录
	 * @param username
	 * @param password
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "checkUserLogin")
	@ResponseBody
	public Json checkUserLogin(String username, String password) {
		Json json = new Json();
		try {
			SysUserService.Client client = (SysUserService.Client) getClient("SysUserService");
			Map<String, String> map = client.checkUserLogin(username, password);
			if (!"200".equals(map.get("key"))) {
				json.setMsg(map.get("value"));
				json.setSuccess(false);
			}else{
			SysLogService.Client clientLog = (SysLogService.Client) getClient("SysLogService");
			SysLog sysLog = new SysLog();
			sysLog.setId(UuidUtils.getUUid());
			sysLog.setCreateTime(DateUtil.getToDayLStr());
			sysLog.setNote(IpUtil.getIp());
			String userId = clientLog.getUserId(username);
			sysLog.setSysuserId(userId);
			sysLog.setLoglevel(1);
			String message = "用户: " + username + Globals.Log_Type_LOGIN_STR;
			
			sysLog.setMessage(message);
			clientLog.insertLog(sysLog);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

		return json;
	}

	@RequestMapping(value = "login")
	public String login() {
		return "index";
	}

	@RequestMapping(value = "logout")
	public String logout() {
		try {
			SysLogService.Client clientLog = (SysLogService.Client) getClient("SysLogService");
			String userId = clientLog.getUserId("admin");
			SysLog sysLog = new SysLog();
			sysLog.setId(UuidUtils.getUUid());
			sysLog.setCreateTime(DateUtil.getToDayLStr());
			sysLog.setNote(IpUtil.getIp());
			sysLog.setSysuserId(userId);
			sysLog.setLoglevel(2);
			String message = "用户: " + "admin" + Globals.Log_Type_EXIT_STR;
			
			sysLog.setMessage(message);
			clientLog.insertLog(sysLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
		
		return "redirect:/";
	}

	/**
	 * 左菜单
	 * @return
	 */
	@RequestMapping(value = "/allTreeNodeSync")
	@ResponseBody
	public List<TreeDTO> allTreeNodeSync() {
		List<TreeDTO> nl = Lists.newArrayList();
		try {
			SysUserService.Client client = (SysUserService.Client) getClient("SysUserService");
			List<TreeDTORPC> treeDTOs = client.findModuleBySelf(null);

			Iterator<TreeDTORPC> iter = treeDTOs.iterator();
			while (iter.hasNext()) {
				TreeDTORPC dto = iter.next();
				TreeDTO treeDTO = new TreeDTO();
				// "state":null,"text":"根模块","id":"1","pid":null,"iconCls":null,"attributes":{"url":"#"}
				treeDTO.setId(dto.getId());
				treeDTO.setPid(dto.getPid());
				treeDTO.setText(dto.getText());
				treeDTO.setIconCls(dto.getIconCls());
				HashMap<String, Object> urlMap = Maps.newHashMap();
				urlMap.put("url", dto.getUrl());
				treeDTO.setAttributes(urlMap);
				nl.add(treeDTO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return nl;
	}

}
