package com.rockwell.ramon.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * TODO Please enter the description of this type. This is mandatory!
 * <p>
 * 
 * @author Brian, 2018年10月30日 下午4:38:27
 */
@Document(collection = "user")
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable
{
	private static final long serialVersionUID = 4533530699165146719L;

	@Id
	private String id;

	@Field("name")
	@NotBlank
	@Indexed(unique = true)
	private String name;
	
	@DBRef
	private List<Role> roles;
	
	private String fName;

	private String lName;

	private Long pwdDuration;

	private LocalDateTime pwdExpiration;

	@NotBlank
//	@Size(min=6,max=32)
//	@Pattern(regexp="^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])).*$")
	private String pwd;

	private String deptId;

	@Email
	private String email;

	private String mobile;

	private Integer sex;

	private Date birthday;

	private Integer status;

	private String picId;

	private String hobby;

	private String address;

	private String memo;

	private LocalDateTime cTime;
	
	private LocalDateTime uTime;
}
