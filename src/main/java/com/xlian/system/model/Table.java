package com.xlian.system.model;

import lombok.Data;

import java.util.Date;


@Data
public class Table {
    /**
     * 表的名称
     */
    private String tableName;
    /**
     * 引擎
     */
    private String engine;
    /**
     * 表的备注
     */
    private String tableComment;

    private Date createTime;

}
