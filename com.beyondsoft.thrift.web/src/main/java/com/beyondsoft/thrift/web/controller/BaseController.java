package com.beyondsoft.thrift.web.controller;


import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.beyondsoft.framework.thrift.client.BaseClientFactory;
import com.beyondsoft.framework.thrift.client.ThriftBinaryClientFactory;
import com.beyondsoft.framework.thrift.exception.ThriftException;
import com.beyondsoft.thrift.web.util.SysConfig;
import com.beyondsoft.thrift.web.pageModel.Json;
import com.beyondsoft.thrift.web.pageModel.PageForm;
import com.google.common.collect.Maps;

public class BaseController {

	protected Map<String, String> getPageRow(PageForm pageform) {
		String a = String
				.valueOf((pageform.getPage() - 1) * pageform.getRows());
		String b = String.valueOf(pageform.getRows());

		Map<String, String> map = Maps.newHashMap();
		map.put("page", a);
		map.put("rows", b);
		return map;
	}

	protected Json getSuccessObj(String msg) {
		Json json = new Json();
		if (StringUtils.isNotEmpty(msg)) {
			json.setMsg(msg);
		}

		return json;
	}

	protected Json getSuccessObj(String code, String msg) {
		Json json = new Json();
		json.setCode(code);
		json.setMsg(msg);
		return json;
	}

	protected Json getFailedObj(String msg) {
		Json json = new Json();
		json.setCode("400");
		json.setMsg(msg);
		json.setSuccess(false);
		return json;
	}

	protected Json getFailedObj(String code, String msg) {
		// 200 success
		// 400 Bad Request - i.e. validation errors.
		// 401 Unauthorized - user is not logged in, cannot access resource.
		// 500 Server error.
		Json json = new Json();
		json.setCode(code);
		json.setMsg(msg);
		json.setSuccess(false);
		return json;
	}

	@Resource(name = "sysConfig")
	private SysConfig sysConfig;



	public Object getClient(String serviceName) throws ThriftException {
		String serviceModuel = serviceName.replaceAll("Service", "")
				.toLowerCase();

		String service = new StringBuffer().append(sysConfig.getRpcPackage())
				.append(".").append(serviceModuel).append(".")
				.append(serviceName).toString();

		BaseClientFactory proxy = new ThriftBinaryClientFactory(
				sysConfig.getThriftServerIp(), sysConfig.getThriftServerPort(),
				sysConfig.getThriftTimeout(), service);

		return proxy.getClient();
	}

	public SysConfig getSysConfig() {
		return sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

}
