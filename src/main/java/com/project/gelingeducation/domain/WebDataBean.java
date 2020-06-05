package com.project.gelingeducation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * web相关参数保存
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "web_data_bean")
@Setter
@Getter
public class WebDataBean implements Serializable {

    private static final long serialVersionUID = -5521843474565227746L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 所有登录人数
     */
    @Column(name = "all_login_mun")
    private Long allLoginMun;

    /**
     * 用来判断是否今天登录过
     */
    @Column(name = "today_login_time")
    private Date todayLoginTime;

    /**
     * 今天登录人数
     */
    @Column(name = "today_login_mun")
    private Long todayLoginMun;

    /**
     * 今天登录的ip数量
     */
    @Column(name = "today_login_ip_mun")
    private Long todayLoginIpMun;


}
