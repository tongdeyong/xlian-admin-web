package com.xlian.system.model;

import lombok.Data;

import java.util.Date;

@Data
public class RoleMenu {
    private Integer id;
    private Integer roleId;
    private Integer menuId;
    private Date createTime;
    private Date updateTime;
}
