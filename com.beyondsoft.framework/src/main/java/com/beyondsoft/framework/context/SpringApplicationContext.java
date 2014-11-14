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
 * Oct 30, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */

package com.beyondsoft.framework.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 类描述：<br> 
 * Spring ApplicationContext 
 * @author  Simon.Hoo
 * @date    Oct 30, 2014
 * @version v1.0
 */
public class SpringApplicationContext implements ApplicationContextAware{

	private static ApplicationContext context;//声明一个静态变量保存

	
	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}
	
	public static ApplicationContext getContext(){
		  return context;
	}

	public static Object getBean(String beanName){
		  return context.getBean(beanName);
	}
}


