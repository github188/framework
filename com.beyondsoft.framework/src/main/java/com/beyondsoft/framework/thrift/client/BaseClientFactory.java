/*
 **************************************************************************
 * 版权声明：
 * 本软件为博彥科技(深圳)有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * Thrift客户端抽象工厂
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * Oct 20, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */

package com.beyondsoft.framework.thrift.client;

import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.beyondsoft.framework.thrift.exception.ThriftException;

/**
 * 类描述：<br> 
 * Thrift客户端抽象工厂
 * @author  Simon.Hoo
 * @date    Oct 20, 2014
 * @version v1.0
 */
public abstract class BaseClientFactory {
	
	private TTransport transport;
	
	/**
	 * Thrift 服务类名(含包名） 
	 */
	protected String thriftService;
	
	/**
	 * 服务端主机
	 */
	protected String hostIp;
	
	/**
	 * 服务端端口
	 */
	protected int port;
	
	/**
	 * 超时时长
	 */
	protected int timeout = 2000;
	
	public BaseClientFactory(){	
	}
	
	public BaseClientFactory(String hostIp,int port,int timeout,String thriftService){
		this.hostIp = hostIp;
		this.port = port;
		this.timeout = timeout;
		this.thriftService = thriftService;
	}
	
	/**
	 * 初始化，创建TTransport实例
	 * @throws ThriftException
	 */
	public void init() throws ThriftException{
		try {
			if(timeout>0){
				transport = new TSocket(hostIp,port, timeout);
			}else{
				transport = new TSocket(hostIp,port);
			}
			transport.open();
		} catch (TTransportException e) {
			throw new ThriftException(e);
		}
	}
	
	/**
	 * 获取Thrift Client对象， 反映射机制。
	 * @return
	 * @throws ThriftException
	 */
	public abstract Object getClient() throws ThriftException;
	
	/**
	 * 销毁资源，关闭TTransport连接。
	 * @throws ThriftException
	 */
	public void destroy() throws ThriftException {
		try{
			if(transport!=null){
				transport.close();
				transport = null;
			}
		}catch(Exception e){
			throw new ThriftException(e);
		}
	}
	
	
	public String getThriftService() {
		return thriftService;
	}
	
	public void setThriftService(String thriftService) {
		this.thriftService = thriftService;
	}
	
	public String getHostIp() {
		return hostIp;
	}
	
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getTimeout() {
		return timeout;
	}
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public TTransport getTransport() {
		return transport;
	}

	public void setTransport(TTransport transport) {
		this.transport = transport;
	}
}


