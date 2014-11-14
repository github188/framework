/*
 **************************************************************************
 * 版权声明：
 * 本软件为博彥科技(深圳)有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * Nov 3, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */

package com.beyondsoft.thrift.web.util;

import org.springframework.stereotype.Component;

/**
 * 类描述：<br> 
 * 系统配置参数
 * @author  Simon.Hoo
 * @date    Nov 3, 2014
 * @version v1.0
 */
@Component("sysConfig")
public class SysConfig {
	
	private String thriftServerIp;
	private int thriftServerPort;
	private String rpcPackage;	
	private int thriftTimeout;

	public String getThriftServerIp() {
		return thriftServerIp;
	}

	public void setThriftServerIp(String thriftServerIp) {
		this.thriftServerIp = thriftServerIp;
	}

	public int getThriftServerPort() {
		return thriftServerPort;
	}

	public void setThriftServerPort(int thriftServerPort) {
		this.thriftServerPort = thriftServerPort;
	}

	public String getRpcPackage() {
		return rpcPackage;
	}

	public void setRpcPackage(String rpcPackage) {
		this.rpcPackage = rpcPackage;
	}

	public int getThriftTimeout() {
		return thriftTimeout;
	}

	public void setThriftTimeout(int thriftTimeout) {
		this.thriftTimeout = thriftTimeout;
	}
	
	
}


