package com.project.gelingeducation.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 页码总数
     */
    private long totalPages;

    /**
     * 总数量
     */
    private long totalRows;

    /**
     * 数据模型
     */
    private Object lists;

}
