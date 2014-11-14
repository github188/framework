package com.beyondsoft.thrift.web.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beyondsoft.thrift.rpc.DataGridRPC;
import com.beyondsoft.thrift.rpc.PageFormRPC;
import com.beyondsoft.thrift.rpc.TreeDTORPC;
import com.beyondsoft.thrift.rpc.sysrole.SysRole;
import com.beyondsoft.thrift.rpc.sysrole.SysRoleService;
import com.beyondsoft.thrift.web.pageModel.DataGrid;
import com.beyondsoft.thrift.web.pageModel.Json;
import com.beyondsoft.thrift.web.pageModel.PageForm;
import com.beyondsoft.thrift.web.pageModel.TreeDTO;
import com.beyondsoft.thrift.web.pageModel.Trole;
import com.beyondsoft.thrift.web.pageModel.Tuser;
import com.beyondsoft.thrift.web.util.DateUtil;
import com.beyondsoft.thrift.web.util.UuidUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/roleController")
public class roleController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(roleController.class);
	
	private static final String ROLELIST = "security/role/list";

	private static final String roleAdd = "security/role/roleAdd";

	private static final String moduleTree = "security/role/moduleTree";
	
	/**
     * 功能: 跳转到角色列表页面
     * 
     * @return
     */
    @RequestMapping(value = "/toPage")
    public String treePage() {
        return ROLELIST;
    }

    /**
     * 功能: 跳转到角色添加页面
     * 
     * @return
     */
    @RequestMapping("/toAddPage")
    public String toAddPage() {
        return roleAdd;
    }

    /**
     * 功能: 跳转到角色对应的模块页面
     * 
     * @return
     */
    @RequestMapping("/toModule")
    public String toModule() {
        return moduleTree;
    }
    

    /**
     * 功能:角色列表
     * 
     * @param pageform
     * @return
     * @throws TException 
     */
    @RequestMapping("/datagrid")
    @ResponseBody
    public DataGrid datagrid(PageForm pageform,SysRole role){
        DataGrid dg = new DataGrid();
        PageFormRPC pg = new PageFormRPC();
		pg.setRows(pageform.getRows());
		pg.setPage(pageform.getPage());
		List<Trole> roleList = Lists.newArrayList();
    	try {
			SysRoleService.Client client = (SysRoleService.Client)getClient("SysRoleService");
			DataGridRPC dgRpc = client.findRole(pg);
			List<PageFormRPC> list = dgRpc.getRows();
			Iterator<PageFormRPC> iter = list.iterator();
			while (iter.hasNext()) {
			PageFormRPC map =iter.next();
			Trole roles = new Trole();
			roles.setId(map.getValue1());
            roles.setName(map.getValue2());			
			roles.setUserName(map.getValue3());
			roleList.add(roles);
			}
			dg.setTotal(dgRpc.getTotal());
			dg.setRows(roleList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return dg;
    }
    /**
     * 功能: 保存新建的角色
     * 
     * @param role
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Json save(SysRole role) {
        Json j = getSuccessObj("");
        role.setId(UuidUtils.getUUid());
        role.setCreateTime(DateUtil.getToDayLStr());
        try {
        	SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
        	int a = client.addRole(role);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.toString());
            j.setSuccess(false);
            j.setMsg("操作失败！");
        }
        return j;
    }

    /**
     * 功能: 编辑角色
     * 
     * @param role
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(SysRole role) {
        Json j = getSuccessObj("");
        role.setCreateTime(DateUtil.getToDayLStr());
        try {
        	SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
        	int a = client.editRole(role);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.toString());
            j.setSuccess(false);
            j.setMsg("操作失败！");
        }
        return j;
    }

    /**
     * 功能: 删除角色
     * 
     * @param role
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(SysRole role) {
        Json j = getSuccessObj("");
        try {
        	SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
        	int a = client.delRole(role);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.toString());
            j.setSuccess(false);
            j.setMsg("是否有关联用户记录没有被删除！");
        }
        return j;
    }

    /**
     * 功能: 超级管理员不允许删除
     * 
     * @param trole
     * @return
     * @throws  
     */
    @RequestMapping("/ifDelete")
    @ResponseBody
    public Json ifDelete(SysRole role){

        Json j = getSuccessObj("");
    	try {
			SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
			int a  = client.ifDelete(role);
			if(a==0){
				j.data.put("not", "not");
	        	return j;
			} if(a==1){
				j.setSuccess(false);
				j.setMsg("操作失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
			j.setSuccess(false);
            j.setMsg("操作失败");
		}
        return j;
    }
    
    /**
     * 功能:移除该角色下的用户
     * 
     * @param trole
     * @return
     */
    @RequestMapping("/removeUser")
    @ResponseBody
    public Json removeUser(SysRole trole) {
        Json j = getSuccessObj("");
    	try {
			SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
			client.removeUser(trole);
    	} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
    	}
        return j;
    }

    /**
     * 功能:添加用户到该角色下
     * 
     * @param trole
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public Json addUser(SysRole trole) {
        Json j = getSuccessObj("");
    	try {
			SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
			client.addUser(trole);
    	} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
    	}
        return j;
    }
    /**
     * 功能: 角色下已分配用户列表
     * @return
     */
    @RequestMapping("/assignedUserDatagrid")
    @ResponseBody
    public DataGrid assignedUserDatagrid(SysRole trole, PageForm pageform) {
        DataGrid dg = new DataGrid();
        PageFormRPC pg = new PageFormRPC();
		pg.setRows(pageform.getRows());
		pg.setPage(pageform.getPage());
		List<Tuser> userList = Lists.newArrayList();
    	try {
			SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
			DataGridRPC dgRpc=client.assignedUserDatagrid(trole,pg);
			List<PageFormRPC> list = dgRpc.getRows();
			Iterator<PageFormRPC> iter = list.iterator();
			while (iter.hasNext()) {
			   PageFormRPC map =iter.next();
			   Tuser user = new Tuser();
			   user.setId(map.getValue1());
			   user.setUsername(map.getValue2());
			   user.setRealname(map.getValue3());
			   user.setStatus(map.getValue4());
			   user.setOrgName(map.getValue5());
			   userList.add(user);
			}
			dg.setTotal(dgRpc.getTotal());
			dg.setRows(userList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return dg;
    }

    /**
     * 功能: 角色下未分配用户列表
     * @return
     */
    @RequestMapping("/noAssignedUserDatagrid")
    @ResponseBody
    public DataGrid noAssignedUserDatagrid(SysRole trole, PageForm pageform) {
        DataGrid dg = new  DataGrid();
        List<Tuser> userList = Lists.newArrayList();
        PageFormRPC pg = new PageFormRPC();
		pg.setRows(pageform.getRows());
		pg.setPage(pageform.getPage());
    	try {
			SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
			DataGridRPC dgRpc=client.noAssignedUserDatagrid(trole, pg);
			List<PageFormRPC> list = dgRpc.getRows();
			Iterator<PageFormRPC> iter = list.iterator();
			while (iter.hasNext()) {
			   PageFormRPC map =iter.next();
			   Tuser user = new Tuser();
			   user.setId(map.getValue1());
			   user.setUsername(map.getValue2());
			   user.setRealname(map.getValue3());
			   user.setStatus(map.getValue4());
			   user.setOrgName(map.getValue5());
			   userList.add(user);
			}
			dg.setTotal(dgRpc.getTotal());
			dg.setRows(userList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return dg;
    }
    /**
     * 功能: 同步树角色面版菜单功能树展示(所有可用菜单全部显示)
     * @return
     */
    @RequestMapping("/menuListSync")
    @ResponseBody
    public List<TreeDTO> menuListSync() {
    	List<TreeDTO> treelist = Lists.newArrayList();
    	try {
			SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
			List<TreeDTORPC> list=client.menuListSync();
			Iterator<TreeDTORPC> iter = list.iterator();
        	while (iter.hasNext()) {
        		TreeDTO tree = new TreeDTO();
				TreeDTORPC dto = iter.next();
				tree.setId(dto.getId());
		        tree.setText(dto.getText());
		        tree.setPid(dto.getPid());
		        tree.setIconCls(dto.getIconCls());
		        tree.setState(dto.getState());
		        HashMap<String, Object> urlMap = Maps.newHashMap();
		        urlMap.put("url", dto.getUrl());
				tree.setAttributes(urlMap);
				treelist.add(tree);
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return treelist;

    }
    
    /**
     * 功能: 回显勾选的叶子节点
     * @return
     */
    @RequestMapping("/selectTreeNode")
    @ResponseBody
    public Json selectTreeNode(SysRole sysRole) {
        Json json = getSuccessObj("");
    	try {
			SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
			String ids = client.selectTreeNode(sysRole);
			json.setObj(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return json;

    }

    /**
     * 功能: 插入角色对应的菜单模块， 思路：先删除角色下的模块，再添加
     * 
     * @param tro
     * @return
     */

    @RequestMapping("/insertDeleteRoleModule")
    @ResponseBody
    public Json insertDeleteRoleModule(SysRole sysRole) {
        Json json = getSuccessObj("");
    	try {
    		
			SysRoleService.Client client = (SysRoleService.Client) getClient("SysRoleService");
			client.insertDeleteRoleModule(sysRole);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return json;

    } 
    
    
    
    
    
    
    
    
    
}
