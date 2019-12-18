package com.xlian.system.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserRole {
    private Integer id;
    private Integer userId;
    private Integer roleId;
    private Date createTime;
    private Date updateTime;
}
