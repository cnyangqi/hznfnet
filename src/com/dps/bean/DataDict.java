package com.dps.bean;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 数据字典实体
 * 
 * @author yangq(qi.yang.cn@gmail.com)
 * @since 2012-2-12
 */
@Data
@Table("t_data_dict")
public class DataDict {

	/**
	 * 数据字典主键
	 */
	@Id
	@Column("id")
	private Integer id;
	/**
	 * 数据字典类型主键
	 */
	@Column("type_id")
	private Integer typeId;
	/**
	 * 数据字典序列 数据字典排序使用
	 */
	@Column("sequ_num")
	private Long sequNum;
	/**
	 * 数据字典名称
	 */
	@Column("name")
	private String name;
	/**
	 * 字典值
	 */
	@Column("value")
	private String value;
	/**
	 * 字典状态
	 */
	@Column("status")
	private Boolean status;
}