package com.project.gelingeducation.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageResult {
    /**
     * 当前页码
     */
    private Integer currentPage;
    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 页码总数
     */
    private Long totalPages;

    /**
     * 总数量
     */
    private Long totalRows;

    /**
     * 数据模型
     */
    private Object lists;

}
