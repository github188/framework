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
import com.beyondsoft.thrift.rpc.TreeDTORPC;
import com.beyondsoft.thrift.rpc.sysdic.SysDic;
import com.beyondsoft.thrift.rpc.sysdic.SysDicService.Iface;
import com.beyondsoft.thrift.server.mapper.SysDicMapper;
import com.google.common.collect.Maps;

/**
 * 类描述<br>
 * 系统用户业务实现
 * 
 * @author Simon.Hoo
 * @version v1.0
 */
@SuppressWarnings("unchecked")
@Service("sysDicServiceImpl")
@ThriftService(service = "com.beyondsoft.thrift.rpc.sysdic.SysDicService")
public class SysDicServiceImpl extends CommonServiceImpl implements Iface {

	@SuppressWarnings("rawtypes")
	@Resource(name = "sysDicMapper")
	private SysDicMapper sysDicMapper;


	private Logger logger = LoggerFactory.getLogger(SysDicServiceImpl.class);

	@Override
	public int checkCode(String sysDic) throws TException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/**
	 * 根据pid 获取孩子
	 * 
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	private boolean hasChildren(String pid)  throws TException {

		Long cnt = sysDicMapper.hasChildren(pid);
		return cnt == 0 ? false : true;
	}
	
	@Override
	public int deleteDic(String id) throws TException {
		try {
			if (hasChildren(id)) {

				return 0;
			} else {
				return sysDicMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			logger.info("删除字典异常:"+e.getMessage());
			throw new RuntimeException("删除字典异常");
		}
		
	}

	@Override
	public int edit(SysDic sysDic) throws TException {
		try {
			int count = sysDicMapper.updateByPrimaryKeySelective(sysDic);
			return count;
		} catch (Exception e) {
			logger.info("编辑字典异常:"+e.getMessage());
			throw new RuntimeException("编辑字典异常");
		}
	}

	@Override
	public int save(SysDic sysDic) throws TException {
		// TODO Auto-generated method stub
		try {
			sysDicMapper.insertSelective(sysDic);
		} catch (Exception e) {
			logger.info("添加字典异常:"+e.getMessage());
			throw new RuntimeException("添加字典异常");
		}
		
		return 0;
	}

	@Override
	public List<TreeDTORPC> treegrid(String id) throws TException {
		List<TreeDTORPC> treeList = null;
		
		List<SysDic> sysDicList=null;
		sysDicList = sysDicMapper.getDicTree(id);
		
		
		 //将数据库中的值放到treeDao中提供前台使用
        List<TreeDTORPC> dlist = new ArrayList<TreeDTORPC>();
        for(Iterator<SysDic> iterator = sysDicList.iterator();iterator.hasNext();){
        	TreeDTORPC tree = new TreeDTORPC();
        	SysDic sysDic = iterator.next();
        	
        	String idDTO = sysDic.getId();
        	String pidDTO =sysDic.getPid();
        	
        	String nameDTO = sysDic.getDicName();
        	String diCodeNameDTO = sysDic.getDicCode();
        	String orderCode = sysDic.getOrderCode();
        	String status =sysDic.getStatus();
        	
        	
        	String dicCodeDTO = sysDic.getDicParentName();
        	
        	Map<String,String> mapParam = Maps.newHashMap();//不传parentName
        	mapParam.put("parentDicName",dicCodeDTO);
        	
        	tree.setAttributes(mapParam);
        	tree.setId(idDTO);
        	tree.setPid(pidDTO);
        	tree.setText(nameDTO);
        	tree.setText2(diCodeNameDTO);
        	tree.setText4(orderCode);
        	tree.setText3(status);
        	
        	dlist.add(tree);
        }
		
		return dlist;
	}

	

}
