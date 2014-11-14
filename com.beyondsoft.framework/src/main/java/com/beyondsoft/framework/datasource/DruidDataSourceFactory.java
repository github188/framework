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

package com.beyondsoft.framework.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;

/**
 * 类描述：<br> 
 * Taobao Druid数据源实现
 * @author  Simon.Hoo
 * @date    Oct 23, 2014
 * @version v1.0
 */
public class DruidDataSourceFactory implements DataSourceFactory {
	private DataSource dataSource; 
	
	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public void setProperties(Properties properties) {
		try{
			this.dataSource = com.alibaba.druid.pool.DruidDataSourceFactory.createDataSource(properties);
		}catch(RuntimeException e){
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}


