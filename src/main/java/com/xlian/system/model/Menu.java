package com.xlian.system.model;

import lombok.Data;

import java.util.Date;

@Data
public class Menu  {
	//主键
	private Integer id;
	// 父菜单ID，一级菜单为0
	private Integer parentId;
	// 菜单名称
	private String name;
	// 菜单URL
	private String url;
	// 类型 0：目录 1：菜单 2：按钮
	private Integer type;
	// 菜单图标
	private String icon;
	// 排序
	private Integer orderNum;

	private Integer status;
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date updateTime;

}
