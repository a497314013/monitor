package com.rockwell.ramon.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection="user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRole implements Serializable
{
	private static final long serialVersionUID = -4682006097667007114L;
	@Id
	private String id;
	
	@NotNull
	@Indexed
	private String userId;
	
	@NotNull
	@Indexed
	private String roleId;
}
