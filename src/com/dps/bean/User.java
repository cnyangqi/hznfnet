package com.dps.bean;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 用户类
 * 
 * @author yangq(qi.yang.cn@gmail.com)
 */
@Data
@Table("user")
public class User {

	/**
	 * 
	 */
	@Id
	@Column("id")
	private Integer id;
	/**
	 * 
	 */
	@Column("name")
	private String name;
}