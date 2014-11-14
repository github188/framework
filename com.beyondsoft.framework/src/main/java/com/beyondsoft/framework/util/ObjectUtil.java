/*
 **************************************************************************
 * 版权声明：
 * 本软件为博彥科技(深圳)有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 对象操作工具
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * Nov 3, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.beyondsoft.framework.util;

/**
 * 类描述：<br> 
 * 对象操作工具
 * @author  Simon.Hoo
 * @date    Nov 3, 2014
 * @version v1.0
 */
public class ObjectUtil {
	
	
	/**
	 * 如前传入的对象t为null，则通过返映实例货传入的类型实例
	 * 
	 * @param c
	 * @param t
	 * @return
	 */
	public static <T> T getNotNullObject(Class<T> c,T t){
		if(t==null){
			try {
				
				t = c.newInstance();
				
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return t;
	}
}


