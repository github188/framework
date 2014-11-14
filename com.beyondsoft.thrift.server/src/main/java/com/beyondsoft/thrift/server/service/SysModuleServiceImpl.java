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


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.beyondsoft.framework.annotation.ThriftService;
import com.beyondsoft.thrift.rpc.PageFormRPC;
import com.beyondsoft.thrift.rpc.TreeDTORPC;
import com.beyondsoft.thrift.rpc.sysmodule.SysModule;
import com.beyondsoft.thrift.rpc.sysmodule.SysModuleService.Iface;
import com.beyondsoft.thrift.server.mapper.SysModuleMapper;
import com.google.common.collect.Maps;

/**
 * 类描述<br>
 * 系统用户业务实现
 * 
 * @author Simon.Hoo
 * @version v1.0
 */
@SuppressWarnings("unchecked")
@Service("sysModuleServiceImpl")
@ThriftService(service = "com.beyondsoft.thrift.rpc.sysmodule.SysModuleService")
public class SysModuleServiceImpl extends CommonServiceImpl implements Iface {
	/**
	 * Logger for this class
	 */
	private Logger logger = LoggerFactory.getLogger(SysModuleServiceImpl.class);

	@SuppressWarnings("rawtypes")
	@Resource(name = "sysModuleMapper")
	private SysModuleMapper sysModuleMapper;

	@Override
	public int addModule(SysModule module)  throws TException{
		try {
			
			
			int flag = sysModuleMapper.insert(module);
			return flag;
		} catch (Exception e) {
			logger.info("添加模块异常:"+e.getMessage());
			throw new RuntimeException("添加模块异常");
		}

	}

	@Override
	public int delModule(SysModule module)  throws TException{

		String id = module.getId();// 删除父节点的id
		try {
			if (hasChildren(id)) {

				return 0;
			} else {
				return sysModuleMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			logger.info("删除模块异常:"+e.getMessage());
			throw new RuntimeException("删除模块异常");
		}


	}

	@Override
	public int editModule(SysModule module)  throws TException {

		try {
			int flag = sysModuleMapper.updateByPrimaryKey(module);
			return flag;
		} catch (Exception e) {
			logger.info("编辑模块异常:"+e.getMessage());
			throw new RuntimeException("编辑模块异常");
		}
		
	}

	
	@Override
	public List<TreeDTORPC> getModule(String id)  throws TException{
		// TODO Auto-generated method stub

		List<TreeDTORPC> treelist = null;
		try {
			// 获取当前展开的节点id

			treelist = this.getChildrenByParentId(id);
			return treelist;

		} catch (Exception e) {
			logger.info("获取模块异常:"+e.getMessage());
			throw new RuntimeException("获取模块异常");
		}

	}

	private List<TreeDTORPC> getChildrenByParentId(String id)  throws TException{

		List<TreeDTORPC> tlist = new ArrayList<TreeDTORPC>();
		List<PageFormRPC> listRPC = null;
		if ("".equals(id) || id == null) {

			listRPC = sysModuleMapper.selectModuleRoot();

		} else {
			Map<String, String> params = Maps.newHashMap();
			params.put("id", id);
			listRPC = sysModuleMapper.selectModule(params);

		}

		Iterator<PageFormRPC> iter = listRPC.iterator();
		while (iter.hasNext()) {
			PageFormRPC pageFormRPC = iter.next();
			TreeDTORPC tree = new TreeDTORPC();

			String idDTO = pageFormRPC.getValue1();
			String pidDTO = pageFormRPC.getValue2() == null ? "null"
					: pageFormRPC.getValue2();
			String nameDTO = pageFormRPC.getValue3();
			String urlDTO = pageFormRPC.getValue4();
			String priorityDTO = pageFormRPC.getValue5();
			String iconClsDTO = pageFormRPC.getValue6();
			String snDTO = pageFormRPC.getValue7();

			tree.setId(idDTO);
			tree.setText(nameDTO);
			tree.setPid(pidDTO);
			tree.setIconCls(iconClsDTO);

			Map<String, String> map = Maps.newHashMap();
			map.put("url", urlDTO);
			map.put("displayorder", priorityDTO);
			map.put("sn", snDTO);
			tree.setAttributes(map);

			if (hasChildren(idDTO)) {
				tree.setState("closed");
			} else {
				tree.setState("open");
			}

			tlist.add(tree);
		}

		return tlist;
	}

	/**
	 * 根据pid 获取孩子
	 * 
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	private boolean hasChildren(String pid)  throws TException {

		Long cnt = sysModuleMapper.hasChildren(pid);
		return cnt == 0 ? false : true;
	}

}
