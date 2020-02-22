package com.xlian.system.vo;

import lombok.Data;

@Data
public class SqlQueryVO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String sql;
}
