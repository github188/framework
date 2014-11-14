/*
 **************************************************************************
 * 版权声明：
 * 本软件为博彥科技(深圳)有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * Thrift Service实现注解
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * Oct 22, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */

package com.beyondsoft.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类描述：<br> 
 * Thrift Service实现注解
 * @author  Simon.Hoo
 * @date    Oct 22, 2014
 * @version v1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.TYPE})
@Inherited
public @interface ThriftService {
	
	/**
	 * Thrift 服务实现的全名
	 * @return
	 */
	public String service();	
}


