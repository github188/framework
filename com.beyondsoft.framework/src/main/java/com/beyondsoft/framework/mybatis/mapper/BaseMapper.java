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
 * Oct 27, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */

package com.beyondsoft.framework.mybatis.mapper;

import java.util.List;
import java.util.Map;

/**
 * 类描述：<br> 
 * 
 * @author  Simon.Hoo
 * @date    Oct 27, 2014
 * @version v1.0
 * @param <PK>
 */
public interface BaseMapper<T, PK> {
	
	/**
	 * 添加对象
	 * @param o
	 * @return
	 */
	public int insert(T o);
	
	/**
	 * 根据主键删除对象
	 * @param pk
	 * @return
	 */
	public int deleteByPrimaryKey(PK pk);
	
	/**
	 * 根据条件删除对象
	 * @param params
	 * @return
	 */
	public int deleteByMap(Map<String, Object> params);
	
	/**
	 * 删除对象
	 * @param o
	 * @return
	 */
	public int delete(T o);

	/**
	 * 根据主键查询对象
	 * @param pk
	 * @return
	 */
	public T selectByPrimaryKey(PK pk);
	
	/**
	 * 根据条件查询对象列表
	 * @param params
	 * @return
	 */
	public List<T> selectByMap(Map<String, Object> params);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	public List<T> selectAll();
	
	
	/**
	 * 查询所有记录总数
	 * @return
	 */
	public Long selectAllCnt();
	
	
	
	/**
	 * 根据主键更新对象
	 * @param o
	 * @return
	 */
	public int updateByPrimaryKey(T o);

	/**
	 * 根据条件更新对象
	 * @param o
	 * @param params
	 * @return
	 */
	public int updateWithMap(T o,Map<String, Object> params);
}


