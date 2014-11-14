package com.beyondsoft.thrift.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beyondsoft.thrift.rpc.TreeDTORPC;
import com.beyondsoft.thrift.rpc.sysdic.SysDic;
import com.beyondsoft.thrift.rpc.sysdic.SysDicService;
import com.beyondsoft.thrift.web.pageModel.Json;
import com.beyondsoft.thrift.web.pageModel.TreeDTO;
import com.beyondsoft.thrift.web.util.UuidUtils;

@Controller
@RequestMapping("/dicController")
public class DicController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(DicController.class);
	
	private static final String toAddPage = "security/dic/dicAdd";

	private static final String toEditPage = "security/dic/dicEdit";

	private static final String dicTREE = "security/dic/dicTree";
	
	
	/**
	 * 功能：跳转到组织树页面
	 * 
	 * @return	
	 */
	@RequestMapping(value = "/toPage")
	public String toPage(){
		return dicTREE;
	}
	
	/**
	 * 功能：跳转到组织树添加页面
	 * @return
	 */
	@RequestMapping(value = "/toAddPage")
	public String toAddPage(){
		return toAddPage;
	}
	
	/**
	 *功能： 跳转到组织树编辑页面
	 *@return
	 */
	@RequestMapping(value = "/toEditPage")
	public String toEditPage(){
		return toEditPage;
	}
	
	
	/**
	 * 功能：组织树页面加载组织树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/treegrid")
    @ResponseBody
    public List<TreeDTORPC> treegrid(String id) throws Exception{
		List<TreeDTORPC> treeDtos =null;
		try {
			SysDicService.Client client = (SysDicService.Client) getClient("SysDicService");
			treeDtos = client.treegrid(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
		}
		return treeDtos;
	}
	
	/**
	 * 保存节点的方法
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Json save(SysDic d){
		Json j = getSuccessObj("");
		try {
		    SysDicService.Client client = (SysDicService.Client) getClient("SysDicService");
		    SysDic dic = new SysDic();
		    dic.setId(UuidUtils.getUUid());
		    dic.setDicCode(d.getDicCode());
		    dic.setDicName(d.getDicName());
		    dic.setOrderCode(d.getOrderCode());
		    dic.setStatus(d.getStatus());
		    dic.setPid(d.getId());
			int tree =  client.save(dic);;
			j.setObj(tree);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.toString());
			j.setSuccess(false);
		}
		return j;
	}
		
	/**
	 * 更新节点
	 * 
	 * @param request	
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Json edit(SysDic d){
        Json j = getSuccessObj("");
		try {
		SysDicService.Client client = (SysDicService.Client) getClient("SysDicService");
			int d1 = client.edit(d);
			j.setObj(d1);
		} catch (Exception e) {
			e.printStackTrace();
            logger.info(e.toString());
            j.setSuccess(false);
		}
		return j;
	}
		
	/**
	 * 删除节点
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Json delete(String id) throws Exception{
		Json j = getSuccessObj("");
		TreeDTO tree = new TreeDTO();
		try {
		    SysDicService.Client client = (SysDicService.Client) getClient("SysDicService");
			tree.setId(id);
			int a =client.deleteDic(id);
			if(a==0){
				j.setSuccess(false);
				j.setMsg("请先删除子节点！");
			}
			j.setObj(tree);
		} catch (Exception e) {
			e.printStackTrace();
            logger.info(e.toString());
            j.setSuccess(false);
		}
		return j;
	}
	/**
	 * 验证编号是否重复
	 * @param request 
	 * 
	 * @param response
	 */
	@RequestMapping(value = "checkCode")
	public Json checkCode(String dicCode,HttpServletResponse response) throws Exception{
		Json j = getSuccessObj("");
		try {
			SysDicService.Client client = (SysDicService.Client) getClient("SysDicService");
			int num = client.checkCode(dicCode);
			    String result = "";
		        if (num == 0) {
		            result = "true";
		        } else {
		            result = "false";
		        }
		        
		response.getWriter().write(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
            logger.info(e.toString());
            j.setSuccess(false);
		}
		return j;    
	}
}
