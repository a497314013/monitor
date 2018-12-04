package com.rockwell.ramon.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role implements Serializable
{
	private static final long serialVersionUID = 6085709846199099094L;

	@Id
	private String id;
	
	@NotBlank(message="roleName不能为空！")
	@Indexed(unique = true)
	@Size(max=20)
	private String roleName;

	@NotBlank(message="roleSign不能为空！")
	private String roleSign;
	
	@DBRef
	private List<Menu> menus;
	
	private LocalDateTime cTime;
	
	private LocalDateTime uTime;
}
