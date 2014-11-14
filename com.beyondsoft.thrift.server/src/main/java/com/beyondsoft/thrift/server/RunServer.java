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
 * Oct 23, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */

package com.beyondsoft.thrift.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 类描述：<br> 
 * Thrift 服务端启动
 * @author  Simon.Hoo
 * @date    Oct 23, 2014
 * @version v1.0
 */
public class RunServer extends Thread {
	private Logger logger = LoggerFactory.getLogger(RunServer.class);

	public static void main(String[] args) {
		Thread thread = new RunServer();
		thread.start();
	}

	
	@Override
	public void run() {
		try{
			new ClassPathXmlApplicationContext(new String[] {"config/app.xml"});
		} catch (SecurityException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


