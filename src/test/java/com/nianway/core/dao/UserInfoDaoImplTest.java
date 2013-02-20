/**
 * 
 */
package com.nianway.core.dao;

import junit.framework.Assert;

import com.nianway.core.mapping.UserInfo;

/**
 * @author zhizhang.zhou
 * 
 */
public class UserInfoDaoImplTest extends DaoTest {

	UserInfoDaoImpl userInfoDaoImpl = new UserInfoDaoImpl();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		userInfoDaoImpl.setSessionFactory(sessionFactory);
	}

	public void testGetUserInfoByUserName() {
		String userName = "operator";
		UserInfo userInfo = userInfoDaoImpl.getUserInfoByUserName(userName);

		Assert.assertNotNull(userInfo);
		Assert.assertEquals(userName, userInfo.getUserName());
	}

}
