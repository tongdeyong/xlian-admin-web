package com.xlian.system.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleDto {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String roleName;

    private Integer roleId;

    private List<Integer> menuIdList;

}
