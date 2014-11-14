package com.beyondsoft.thrift.web.pageModel;

import java.util.HashMap;
import java.util.Map;

public class Json {

	private String code = "200";

	private boolean success = true;

	
	private String msg = "操作成功";

	private Object obj = null;

	private Map<String, Object> attributes;// 其他参数

	//public List<Object> data = new ArrayList<Object>();// 其他参数
	
	public Map<String, Object> data = new  HashMap<String, Object>();// 其他参数

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getObj() {
		return obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
