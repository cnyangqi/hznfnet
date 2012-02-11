package com.dps.bean;

import java.util.List;

import lombok.Data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

/**
 * 数据字典类型实体
 * 
 * @author yangq(qi.yang.cn@gmail.com)
 * @since 2012-2-12
 */
@Data
@Table("t_data_dict_type")
public class DataDictType {

	/**
	 * 数据字典类型主键
	 */
	@Id
	@Column("id")
	private Integer id;
	/**
	 * 类型名称
	 */
	@Column("name")
	private String name;
	/**
	 * 类型代码
	 */
	@Column("code")
	private String code;
	/**
	 * 类型图标
	 */
	@Column("icon_cls")
	private String iconCls;
	/**
	 * 父级数据字典类型主键
	 */
	@Column("parent_id")
	private Integer parentId;
	/**
	 * 数据字典类型序列 数据字典类型排序使用
	 */
	@Column("sequ_num")
	private Long sequNum;
	/**
	 * 子数据字典类型数量
	 */
	@Column("sub_type_num")
	private Integer subTypeNum;
	/**
	 * 子数据字典数量
	 */
	@Column("sub_dd_num")
	private Integer subDdNum;
	/**
	 * 子数据字典集合
	 */
	@Many(field = "typeId", target = DataDict.class)
	private List<DataDict> dds;
}