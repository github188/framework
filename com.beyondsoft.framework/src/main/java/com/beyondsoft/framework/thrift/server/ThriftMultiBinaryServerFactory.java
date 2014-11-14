/*
 **************************************************************************
 * 版权声明：
 * 本软件为博彥科技(深圳)有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * Thrift服务端工厂
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * Oct 22, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */

package com.beyondsoft.framework.thrift.server;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.beyondsoft.framework.annotation.ThriftService;
import com.beyondsoft.framework.context.SpringApplicationContext;
import com.beyondsoft.framework.hander.ScanPackageHander;
import com.beyondsoft.framework.mybatis.mapper.BaseMapper;
import com.beyondsoft.framework.thrift.exception.ThriftException;

/**
 * 类描述：<br> 
 * Thrift服务端工厂
 * @author  Simon.Hoo
 * @date    Oct 22, 2014
 * @version v1.0
 */
public class ThriftMultiBinaryServerFactory{
	private Logger logger = LoggerFactory.getLogger(ThriftMultiBinaryServerFactory.class);
	
	/**
	 * 服务端TCP端口
	 */
	private Integer port = 9999;
		
	/**
	 * Thrift服务端实现基础包名
	 */
	private String baseServiceImplPackage;
	
	/**
	 * 超时时长
	 */
	private Integer timeout = 2000;
	
	
	/**
	 * Base Mappser
	 */
	@SuppressWarnings("rawtypes")
	private BaseMapper baseMapper;
	
	public ThriftMultiBinaryServerFactory(){}
	
	public ThriftMultiBinaryServerFactory(String baseServiceImplPackage){
		this.baseServiceImplPackage = baseServiceImplPackage;
	}
	
	public ThriftMultiBinaryServerFactory(String baseServiceImplPackage,Integer port){
		this.baseServiceImplPackage = baseServiceImplPackage;
		this.port = port;
	}
	
	public ThriftMultiBinaryServerFactory(String baseServiceImplPackage,Integer port,Integer timeout){
		this.baseServiceImplPackage = baseServiceImplPackage;
		this.port = port;
		this.timeout = timeout;
	}
	
	/**
	 * 启动服务
	 * @return
	 */
	public int start(){
		int result = 0;
		try {
			TServer server = getServer();
			
			System.out.println("Starting server on port "+port+" ...");	
			
			server.serve();		
			
			result = 1;
		} catch (ThriftException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 获取服务端Socket连接
	 * @return
	 * @throws ThriftException
	 */
	public TServerTransport getServerTransport() throws ThriftException {
		try {
			if(timeout>0){
				return new TServerSocket(port,timeout);		
			}else{
				return new TServerSocket(port);
			}
		} catch (TTransportException e) {
			e.printStackTrace();
			throw new ThriftException(e);
		}
	}
	
	/**
	 * 获取Processor实例
	 * @return
	 * @throws ThriftException
	 */
	public TProcessor getProcessor() throws ThriftException{
		try {
			TMultiplexedProcessor processor = new TMultiplexedProcessor();
			
			Set<Class<?>> thriftServiceImplClassList = ScanPackageHander.getPackageAllClasses(baseServiceImplPackage, true);
			
			for(Class<?> thriftServiceImplClass : thriftServiceImplClassList){						
				if(thriftServiceImplClass.isAnnotationPresent(ThriftService.class)){
					ThriftService thriftServiceAnnotation = (ThriftService)thriftServiceImplClass.getAnnotation(ThriftService.class);
					String thriftServiceName = thriftServiceAnnotation.service();
					
					Constructor<?> constructor = Class.forName(thriftServiceName+"$Processor").getConstructor(Class.forName(thriftServiceName+"$Iface"));
			
					Object service = SpringApplicationContext.getBean(getServiceImplBeanName(thriftServiceImplClass));

					processor.registerProcessor(thriftServiceName, (TProcessor) constructor.newInstance(service));
					
					if(logger.isDebugEnabled()){
						logger.debug(">>> Thrift Service implements class: "+thriftServiceImplClass.getName());
					}
				}
			}
			return processor;
		} catch (NoSuchMethodException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		} catch (SecurityException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		} catch (ClassNotFoundException e) {
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
			throw new ThriftException(e);
		} catch (IllegalArgumentException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		} catch (InvocationTargetException e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		} catch (Exception e){
			if(logger.isInfoEnabled()){
				e.printStackTrace();
			}
			throw new ThriftException(e);
		}
	}
	
	/**
	 * 获取Service impl的Spring bean名称，首先根据@Service注解来判断，如果没有则为类的间单名
	 * @param thriftServiceImplClass
	 * @return
	 */
	private String getServiceImplBeanName(Class<?> thriftServiceImplClass){
		if(thriftServiceImplClass.isAnnotationPresent(Service.class)){
			Service serviceAnnotation = (Service)thriftServiceImplClass.getAnnotation(Service.class);			
			String value = serviceAnnotation.value();
			
			if(StringUtils.isEmpty(value)){
				return StringUtils.uncapitalize(thriftServiceImplClass.getSimpleName());
			}else{
				return value;
			}
		}else{
			return StringUtils.uncapitalize(thriftServiceImplClass.getSimpleName());
		}
	}
	
	/**
	 * 获取协议工厂
	 * @return
	 * @throws ThriftException
	 */
	public TProtocolFactory getProtocolFactory() throws ThriftException {
		return new TBinaryProtocol.Factory(true,true);  
	}

	
	/**
	 * 获取服务器
	 * @return
	 * @throws ThriftException
	 */
	public TServer getServer() throws ThriftException {
		return new TThreadPoolServer(new TThreadPoolServer.Args(getServerTransport())
									.protocolFactory(getProtocolFactory())
									.processor(getProcessor()));
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getBaseServiceImplPackage() {
		return baseServiceImplPackage;
	}

	public void setBaseServiceImplPackage(String baseServiceImplPackage) {
		this.baseServiceImplPackage = baseServiceImplPackage;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	@SuppressWarnings("rawtypes")
	public BaseMapper getBaseMapper() {
		return baseMapper;
	}

	@SuppressWarnings("rawtypes")
	public void setBaseMapper(BaseMapper baseMapper) {
		this.baseMapper = baseMapper;
	}
	
}


