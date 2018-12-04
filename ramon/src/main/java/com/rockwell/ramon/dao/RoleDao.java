package com.rockwell.ramon.dao;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.rockwell.ramon.entity.Role;


public interface RoleDao extends MongoRepository<Role, String>,QuerydslPredicateExecutor<Role>
{
	List<Role> findAllByOrderByRoleName();
	Role findByRoleName(String roleName);
	List<Role> findByCTimeBefore(DateTime birthday);
	List<Role> findByCTimeAfter(DateTime birthday);
	List<Role> findDistinctNameBy();
	List<Role> findByRoleNameLike(String roleName);
	List<Role> findByRoleNameIn(String ... roleNames);
}
