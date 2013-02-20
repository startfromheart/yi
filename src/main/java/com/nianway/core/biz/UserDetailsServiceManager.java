/**
 * 
 */
package com.nianway.core.biz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nianway.core.dao.UserInfoDaoImpl;
import com.nianway.core.mapping.UserInfo;

/**
 * @author zhizhang.zhou
 * 
 */
public class UserDetailsServiceManager implements UserDetailsService {

	@Autowired
	private UserInfoDaoImpl userInfoDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		UserInfo userInfo = userInfoDao.getUserInfoByUserName(username);
		if (userInfo == null) {
			return null;
		}
		UserDetails userDetails = convertToUserDetails(userInfo);
		return userDetails;
	}

	private UserDetails convertToUserDetails(UserInfo userInfo) {
		List<SimpleGrantedAuthority> authorityArray = new ArrayList<SimpleGrantedAuthority>();
		authorityArray.add(new SimpleGrantedAuthority("ROLE_AUTHENTICATED"));

		UserDetails userDetails = new User(userInfo.getUserName(),
				userInfo.getPassword(), authorityArray);
		return userDetails;
	}

	public void setUserInfoDao(UserInfoDaoImpl userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

}
