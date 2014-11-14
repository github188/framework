/*
 **************************************************************************
 * 版权声明：
 * 本软件为博彥科技(深圳)有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * Thrfit客户端工厂
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * Oct 20, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */

package com.beyondsoft.framework.thrift.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beyondsoft.framework.thrift.exception.ThriftException;

/**
 * 类描述：<br> 
 * Thrfit客户端工厂
 * @author  Simon.Hoo
 * @date    Oct 20, 2014
 * @version v1.0
 */
public class ThriftBinaryClientFactory extends BaseClientFactory {
	private Logger logger = LoggerFactory.getLogger(ThriftBinaryClientFactory.class);
	
	public ThriftBinaryClientFactory(){
	}
	
	public ThriftBinaryClientFactory(String hostIp,int port,int timeout,String thriftService){
		super(hostIp,port,timeout,thriftService);
	}
	
	
	@Override
	public Object getClient() throws ThriftException {
		Object clientObject = null;
		try{			
			if(super.getTransport()==null){			
				super.init();
			}

			TProtocol protocol = new TBinaryProtocol(super.getTransport());
			
			TMultiplexedProtocol mp = new TMultiplexedProtocol(protocol,thriftService);
			
			Class<?> client =  Class.forName(thriftService+"$Client");
			Constructor<?> constructor = client.getConstructor(TProtocol.class);
			clientObject = constructor.newInstance(mp);
		} catch (SecurityException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		} catch (NoSuchMethodException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		} catch (IllegalArgumentException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		} catch (InstantiationException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		} catch (IllegalAccessException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		} catch (ClassNotFoundException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		} catch(Exception e){
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		}
		return clientObject;
	}
}


