package com.rockwell.ramon.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.rockwell.ramon.entity.User;

/**
 * TODO Please enter the description of this type. This is mandatory!
 * <p>
 * 
 * @author Brian, 2018年10月23日 下午11:48:11
 */
public interface UserDao extends MongoRepository<User, String>,QuerydslPredicateExecutor<User>
{
	@Query("{ 'user_name' : {$regex: ?0, $options: 'i' }}")
	User findAppUserByUserName(final String userName);

	List<User> findByFNameLike(String fName);

	List<User> findByLNameLike(String lName);

	User findByName(String name);

	List<User> findByNameLike(String name);

	Integer countByNameLike(String name);

	List<User> findByBirthdayBefore(Date birthday);

	List<User> findByBirthdayAfter(Date birthday);

	List<User> findByNameLikeAndLNameLike(String name, String lname);

	List<User> findByNameLikeAndStatusLike(String name, String status);

	List<User> findByLNameLikeAndStatusLike(String name, String status);

	List<User> findByNameLikeAndLNameLikeAndStatusLike(String name, String lname,String status);

	Integer countByNameLikeAndLNameLikeAndStatusLike(String name, String lname,String status);
	
	Integer countByLNameLikeAndStatusLike(String name, String lname);
	
	Integer countByNameLikeAndStatusLike(String name, String lname);
	
	Integer countByNameLikeAndLNameLike(String name, String lname);
}
