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

import java.util.Map;

import com.google.common.collect.Maps;

public class CommonServiceImpl implements CommonServiceI {

	@Override
	public Map<String, String> getMap() {

		Map<String, String> map = Maps.newHashMap();
		map.put("key", "200");
		map.put("value", "");
		return map;
	}
}
