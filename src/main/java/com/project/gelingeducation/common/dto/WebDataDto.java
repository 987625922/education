package com.project.gelingeducation.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 首页需要的数据输出dto
 * @Author LL
 */
@Setter
@Getter
public class WebDataDto {

    /**
     * 历史登录总数
     */
    private Integer allLoginNum;

    /**
     * 今天登录ip数量
     */
    private Integer todayLoginIpNum;

    /**
     * 今天登录数量
     */
    private Integer todayLoginNum;

    /**
     * 专题数量
     */
    private Long subjectNum;

    /**
     * 课程数量
     */
    private Long courseNum;

    /**
     * 视频数量
     */
    private Long videosNum;
}
