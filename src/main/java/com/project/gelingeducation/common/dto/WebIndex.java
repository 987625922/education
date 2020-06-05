package com.project.gelingeducation.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class WebIndex {

    /**
     * 用户上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 历史登录总数
     */
    private Long allLoginMun;

    /**
     * 今天登录ip数量
     */
    private Long todayLoginIpMun;

    /**
     * 今天登录数量
     */
    private Long todayLoginMun;
}
