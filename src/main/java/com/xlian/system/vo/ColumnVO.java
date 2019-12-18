package com.xlian.system.vo;

import lombok.Data;

@Data
public class ColumnVO {

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String tableName;

}
