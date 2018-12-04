/*
 * @(#) RoleMenu.java 2018年11月7日 上午9:55:10
 *
 * Copyright 2018 Rockwell Automation, Inc. All rights reserved.
 * Rockwell Automation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */package com.rockwell.ramon.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "role_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleMenu implements Serializable
{
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 3363884071879400220L;

	@Id
	private String id;

	@NotBlank
	@Indexed
	private String roleId;

	@NotBlank
	private String menuId;
}
