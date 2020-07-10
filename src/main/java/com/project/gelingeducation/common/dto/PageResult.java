package com.project.gelingeducation.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: LL
 * @Description: 分页的实体类
 * 备注：
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class PageResult implements Serializable {

    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 页码总数(数据库中数据分页之后的总数)
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
