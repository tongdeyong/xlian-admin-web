package com.xlian.system.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleVO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String roleName;

    private Integer roleId;

    private List<Integer> menuIdList;

}
