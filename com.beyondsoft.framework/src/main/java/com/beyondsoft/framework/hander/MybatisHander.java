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

package com.beyondsoft.framework.hander;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 类描述：<br> 
 * Mybatis 工具类
 * @author  Simon.Hoo
 * @date    Oct 23, 2014
 * @version v1.0
 */
public class MybatisHander {
	private static SqlSessionFactory sqlSessionFactory;  
	
	static{
		String resourceLocation = "config/mybatis-config.xml"; 
		Reader reader = null; 
		try{
			reader = Resources.getResourceAsReader(resourceLocation);  
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try {
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static SqlSession getSqlSession(){  
		return sqlSessionFactory.openSession(); 
	}
	
	public static void destory(SqlSession session){  
		if(session!=null){
			session.close();
		}
	}
}


