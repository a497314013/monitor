package com.rockwell.ramon.service;

import com.rockwell.ramon.dao.UserDao;
import com.rockwell.ramon.entity.User;

/**
 * TODO Please enter the description of this type. This is mandatory!
 * <p>
 * 
 * @author Brian, 2018年10月26日 下午3:09:48
 */
public interface UserService
{
	UserDao getUserDao();
	
	User save(User user);
}
