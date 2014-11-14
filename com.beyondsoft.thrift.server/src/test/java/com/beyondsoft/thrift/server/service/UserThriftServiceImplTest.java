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
 

package com.beyondsoft.thrift.server.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.beyondsoft.framework.context.SpringApplicationContext;
import com.beyondsoft.thrift.rpc.UserStruct;

*//**
 * 类描述：<br> 
 * Thrift 服务实现 单元测试
 * @author  Simon.Hoo
 * @date    Oct 30, 2014
 * @version v1.0
 *//*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/app-test.xml")
@TestExecutionListeners({ 
    DependencyInjectionTestExecutionListener.class, 
    DirtiesContextTestExecutionListener.class, 
    TransactionalTestExecutionListener.class })
@Transactional
public class UserThriftServiceImplTest {

	private UserThriftServiceImpl service;

	@Before
	public void setUp() throws Exception {
		try{
			service = (UserThriftServiceImpl) SpringApplicationContext.getBean("userThriftServiceImpl"); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testAddUser() {
		try{
			UserStruct userStruct = new UserStruct();
			userStruct.setUserId(99);
			userStruct.setUserName("xxx");
			userStruct.setPassword("123456");
			userStruct.setNikeName("XXX");
			userStruct.setSex("1");
			userStruct.setStatus("0");
			UserStruct addedUserStruct = service.addUser(userStruct);
			
			assertNotNull(addedUserStruct);
			assertEquals(userStruct.getUserId(),addedUserStruct.getUserId());	
			assertEquals(userStruct.getUserName(),addedUserStruct.getUserName());
		}catch(Exception e){
			fail(e.getMessage());
			e.printStackTrace();
		}
		
	}

	@Test
	public void testDeleteUserById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUserByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUser() {
		fail("Not yet implemented");
	}

}


*/