package com.rockwell.ramon.entity;

import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.index.Indexed;
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
 * @author Administrator, 2018年11月7日 上午9:23:18
 */
@Document(collection = "menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Menu
{
	@Id
	private String id;
	
	private String parentId;
	
	@NotBlank
	@Indexed(unique = true)
	private String name;
	
	private String url;
	
	private String perms;
	
	private Integer type;
	
	private String icon;
	
	private Integer sequence = 0;
	
	@Field("cTime")
	private LocalDateTime ctime;
	
	@Field("uTime")
	private LocalDateTime utime;
}
