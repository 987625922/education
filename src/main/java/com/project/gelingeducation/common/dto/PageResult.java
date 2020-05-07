package com.project.gelingeducation.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum = 0;
    /**
     * 每页数量
     */
    private int pageSize = 0;

    /**
     * 页码总数
     */
    private long totalPages = 0;

    /**
     * 总数量
     */
    private long totalRows;

    /**
     * 数据模型
     */
    private Object lists;

}
