package com.dps.bean;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 用户实体
 * 
 * @author yangq(qi.yang.cn@gmail.com)
 */
@Data
@Table("t_user")
public class User {

	/**
	 * 用户主键
	 */
	@Id
	@Column("id")
	private Integer id;
	/**
	 * 用户账户
	 */
	@Column("login_name")
	private String loginName;
	/**
	 * 账户密码
	 */
	@Column("password")
	private String password;
	/**
	 * 用户姓名
	 */
	@Column("name")
	private String name;
	/**
	 * 用户昵称
	 */
	@Column("nickname")
	private String nickname;
	/**
	 * 手机号码
	 */
	@Column("mobi")
	private String mobi;
	/**
	 * 固定电话
	 */
	@Column("tel")
	private String tel;
	/**
	 * QQ号码
	 */
	@Column("qq")
	private String qq;
	/**
	 * 电子邮件
	 */
	@Column("email")
	private String email;
	/**
	 * 找回密码问题 由数据字典动态感配置
	 */
	@Column("question")
	private Integer question;
	/**
	 * 找回密码答案
	 */
	@Column("answer")
	private String answer;
	/**
	 * 账户状态 0禁用/1激活
	 */
	@Column("status")
	private Boolean status;
}