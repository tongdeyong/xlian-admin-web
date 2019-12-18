package com.xlian.system.model;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
	
	private Integer id;
	private String roleName;
	private String roleSign;
	private String remark;
	private Date createTime;
	private Date updateTime;

}
