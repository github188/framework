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
 * Oct 22, 2014     Simon.Hoo		Initial Version.
 *************************************************************************
 */

package com.beyondsoft.framework.hander;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 类描述：<br> 
 * 类扫描单元测试用例
 * @author  Simon.Hoo
 * @date    Oct 22, 2014
 * @version v1.0
 */
public class ScanPackageHanderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPackageAllClasses() {
		Set<Class<?>>  set = ScanPackageHander.getPackageAllClasses(ScanPackageHanderTest.class.getPackage().getName(), true);
		assertEquals(true, getClass(set));
	}

	private boolean getClass(Set<Class<?>>  set){
		for(Class<?> clz : set){
			try {
				if(clz.newInstance() instanceof ScanPackageHanderTest){
					return true;
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}


