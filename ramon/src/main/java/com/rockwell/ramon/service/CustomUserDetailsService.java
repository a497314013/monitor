package com.rockwell.ramon.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rockwell.ramon.entity.Role;
import com.rockwell.ramon.entity.User;

@Component
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userName)
		throws UsernameNotFoundException
	{
		User user = userService.getUserDao().findByName(userName);
		if (user == null)
		{
			throw new UsernameNotFoundException("UserName " + userName + " not found");
		}
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		List<Role> roles = user.getRoles();
		if(roles != null && roles.size() > 0) {
			roles.forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			});
		}
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPwd()
			, authorities);
	}

}
