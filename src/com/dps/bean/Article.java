package com.dps.bean;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 文章实体
 * 
 * @author yangq(qi.yang.cn@gmail.com)
 * @since 2012-2-12
 */
@Data
@Table("t_article")
public class Article {

	/**
	 * 文章主键
	 */
	@Id
	@Column("id")
	private Integer id;
	/**
	 * 文章类型
	 */
	@Column("type")
	private Integer type;
	/**
	 * 文章序列 文章排序使用
	 */
	@Column("sequ_num")
	private Long sequNum;
	/**
	 * 文章标题
	 */
	@Column("title")
	private String title;
	/**
	 * 文章内容
	 */
	@Column("content")
	private String content;
	/**
	 * 文章来源
	 */
	@Column("source")
	private String source;
	/**
	 * 文章作者
	 */
	@Column("author")
	private String author;
	/**
	 * 文章地址
	 */
	@Column("url")
	private String url;
	/**
	 * 发布账户
	 */
	@Column("publish_account")
	private String publishAccount;
	/**
	 * 创建日期
	 */
	@Column("create_date")
	private java.util.Date createDate;
	/**
	 * 修改日期
	 */
	@Column("modify_date")
	private java.util.Date modifyDate;
	/**
	 * 点击次数
	 */
	@Column("click_number")
	private Integer clickNumber;
	/**
	 * 文章是否置顶 0不置顶/1置顶
	 */
	@Column("top_status")
	private Boolean topStatus;
	/**
	 * 文章是否显示 0不显示/1显示
	 */
	@Column("show_status")
	private Boolean showStatus;
	/**
	 * 文章是否审核通过 0未审核通过/1已审核通过
	 */
	@Column("review_status")
	private Boolean reviewStatus;

}