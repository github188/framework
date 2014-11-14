/*
 **************************************************************************
 * 版权声明：
 * 本软件为博彥科技(深圳)有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * Thrift 自定义异常
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * Oct 20, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */

package com.beyondsoft.framework.thrift.exception;

/**
 * 类描述：<br> 
 * Thrift 自定义异常
 * @author  Simon.Hoo
 * @date    Oct 20, 2014
 * @version v1.0
 */
public class ThriftException extends Exception {
	private static final long serialVersionUID = 1142777352822435549L;

	public ThriftException() {
		super();
	}

	public ThriftException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ThriftException(String message, Throwable cause) {
		super(message, cause);
	}

	public ThriftException(String message) {
		super(message);
	}

	public ThriftException(Throwable cause) {
		super(cause);
	}
}


