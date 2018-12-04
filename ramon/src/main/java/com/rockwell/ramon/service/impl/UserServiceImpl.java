package com.rockwell.ramon.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rockwell.ramon.backend.util.md5.EncryptionByMD5;
import com.rockwell.ramon.dao.UserDao;
import com.rockwell.ramon.entity.User;
import com.rockwell.ramon.service.UserService;

@Service("UserService")
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;

	@Override
	public UserDao getUserDao()
	{
		return this.userDao;
	}

	@Override
	public User save(User user)
	{
		user.setUTime(LocalDateTime.now());
		if(user.getId() == null || "".equals(user.getId()))
		{
			user.setCTime(LocalDateTime.now());
			user.setPwd(EncryptionByMD5.encryptionByMD5(user.getPwd().getBytes()));
		}
		else
		{
			//update pwd
			Optional<User> optional = userDao.findById(user.getId());
			if(optional.get() != null && !optional.get().getPwd().equals(user.getPwd()))
			{
				user.setPwd(EncryptionByMD5.encryptionByMD5(user.getPwd().getBytes()));
			}
		}
		
		return userDao.save(user);
	}

}
