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

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.beyondsoft.framework.annotation.ThriftService;
import com.beyondsoft.thrift.rpc.syslog.SysLog;
import com.beyondsoft.thrift.rpc.syslog.SysLogService.Iface;
import com.beyondsoft.thrift.rpc.sysuser.SysUser;
import com.beyondsoft.thrift.server.mapper.SysLogMapper;
import com.beyondsoft.thrift.server.mapper.SysUserMapper;
import com.google.common.collect.Maps;

/**
 * 类描述<br>
 * 系统用户业务实现
 * 
 * @author Simon.Hoo
 * @version v1.0
 */
@SuppressWarnings("unchecked")
@Service("sysLogServiceImpl")
@ThriftService(service = "com.beyondsoft.thrift.rpc.syslog.SysLogService")
public class SysLogServiceImpl extends CommonServiceImpl implements Iface {

	@SuppressWarnings("rawtypes")
	@Resource(name = "sysLogMapper")
	private SysLogMapper sysLogMapper;

	@Resource(name = "sysUserMapper")
	private SysUserMapper sysUserMapper;

	private Logger logger = LoggerFactory.getLogger(SysLogServiceImpl.class);

	@Override
	public long findLogCnt() throws TException {
		// TODO Auto-generated method stub
		return sysLogMapper.selectAllCnt();
	}

	

	@Override
	public int insertLog(SysLog log) throws TException {

		try {
			sysLogMapper.insert(log);
		} catch (Exception e) {
			logger.info("添加日志异常:"+e.getMessage());
			throw new RuntimeException("添加日志异常");
		}

		return 0;
	}

	@Override
	public String getUserId(String userName) throws TException {

		String id = "";
		if (StringUtils.isNotEmpty(userName)) {
			Map<String, String> params = Maps.newHashMap();
			params.put("userName", userName);

			List<SysUser> usrList = sysUserMapper.getSysUserId(params);
			if (usrList != null && usrList.size() > 0) {
				id = usrList.get(0).getId();
			}

		}
		return id;
	}



	@Override
	public List<SysLog> findLogList(Map<String, String> map) throws TException {

			Map<String, Object> params = Maps.newHashMap();
			if (map != null && map.size() > 0) {
				params.put("page",new Integer(map.get("page")) );// page
				params.put("rows", new Integer(map.get("rows")));// size
			}
			return sysLogMapper.selectByMap(params);
	}

}
