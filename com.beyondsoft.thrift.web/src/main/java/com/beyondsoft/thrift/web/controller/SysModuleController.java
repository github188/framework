package com.beyondsoft.thrift.web.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beyondsoft.thrift.rpc.TreeDTORPC;
import com.beyondsoft.thrift.rpc.sysmodule.SysModule;
import com.beyondsoft.thrift.rpc.sysmodule.SysModuleService;
import com.beyondsoft.thrift.web.pageModel.Json;
import com.beyondsoft.thrift.web.pageModel.TreeDTO;
import com.beyondsoft.thrift.web.util.UuidUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


@Controller
@RequestMapping("/moduleController")
public class SysModuleController extends BaseController{

	  private static final Logger logger = Logger.getLogger(SysModuleController.class);
	
	  private static final String TREE = "security/module/menuTree";

	  /**
	    * 功能: 跳转到模块树页面
	    * 
	    * @return
	    */
	  @RequestMapping(value = "/toPage")
	  public String treePage() {
	       return TREE;
	  }
	  
	  /**
	    * 功能: 模块菜单异步树
	    * 
	    * @param request
	    * @param response
	    * @return
	    * @throws Exception
	    */
	  @RequestMapping(value = "/menuList")
	  @ResponseBody
	  public List<TreeDTO> menuList(String id) throws Exception {
	     List<TreeDTO> treelist = Lists.newArrayList();
	        try {
	        	SysModuleService.Client client =  (SysModuleService.Client) getClient("SysModuleService");
	        	List<TreeDTORPC> list = client.getModule(id);
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
			        HashMap<String, String> map = (HashMap<String, String>) dto.getAttributes();
			        Iterator iter1 = map.keySet().iterator();
			        while (iter1.hasNext()) {
			        	 Object map1=iter1.next();
			        	 urlMap.put("displayorder",map.get("displayorder"));
			        	 urlMap.put("url", map.get("url"));
			        	 urlMap.put("sn",map.get("sn"));
			        }
					tree.setAttributes(urlMap);
					treelist.add(tree);
	        	}
	        } catch (Exception e) {
	            logger.info(e.toString());
	            e.printStackTrace();
	        }
	        return treelist;
	    }
	  
	  /**
	     * 保存节点的方法
	     * 
	     * @param request
	     * @param response
	     */
	    @RequestMapping(value = "/add")
	    @ResponseBody
	    public Json save(SysModule module) throws Exception {

	        Json j = getSuccessObj("");

	        try {
	        	SysModuleService.Client client =  (SysModuleService.Client)getClient("SysModuleService");
	        	module.setId(UuidUtils.getUUid());
	        	int flag = client.addModule(module);
	        } catch (Exception e) {
	            logger.info(e.toString());
	            e.printStackTrace();
	            j.setSuccess(false);
	            j.setMsg("操作失败！");
	        }

	        return j;
	    }

	    /**
	     * 更新节点
	     * 
	     * @param request
	     * @param response
	     */
	    @RequestMapping(value = "/edit")
	    @ResponseBody
	    public Json edit(SysModule module) throws Exception {

	        Json j = getSuccessObj("");

	        try {
	        	SysModuleService.Client client =  (SysModuleService.Client) getClient("SysModuleService");
	        	int a = client.editModule(module);
	        } catch (Exception e) {
	            logger.info(e.toString());
	            e.printStackTrace();
	            j.setSuccess(false);
	            j.setMsg("操作失败！");
	        }

	        return j;

	    }

	    /**
	     * 删除节点
	     * 
	     * @param request
	     * @param response
	     */
	    // @RequiresPermissions("Module:delete")
	    @RequestMapping(value = "/delete")
	    @ResponseBody
	    public Json delete(SysModule module) throws Exception {
	        Json j = getSuccessObj("");

	        try {
	        	SysModuleService.Client client =  (SysModuleService.Client) getClient("SysModuleService");
	            int a = client.delModule(module);
	            if(a==0)
	            {
	            	j.setSuccess(false);
	 	            j.setMsg("先删子节点！");
	            }
	           
	            
	        } catch (Exception e) {
	            logger.info(e.toString());
	            e.printStackTrace();
	           
	        }

	        return j;
	    }
	  
	  
	  
	  
	  
	    
}
