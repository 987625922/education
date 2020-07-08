package com.project.gelingeducation.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LL
 * @Description: 登录日志实体类
 */
@Entity
@Table(name = "login_log")
@Accessors(chain = true)
@Setter
@Getter
public class LoginLog implements Serializable {

    private static final long serialVersionUID = -7554456064719088058L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 第一次登录时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "first_login_time")
    @CreationTimestamp
    private Date firstLoginTime;

    /**
     * 登录时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 上次登录时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 登录地点
     */
    @Column(name = "location", length = 48)
    private String location;

    /**
     * 操作系统
     */
    @Column(name = "user_system", length = 20)
    private String userSystem;

    /**
     * 登录浏览器
     */
    @Column(name = "browser", length = 20)
    private String browser;

    /**
     * 登录 IP
     */
    @Column(name = "ip", length = 20)
    private String ip;

    /**
     * 用户表的id
     */
    @Column(name = "uid", insertable = false, updatable = false)
    private Long uid;

    /**
     * @JoinColumn 会在这张表维护一个名为uid的外键id
     */
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "uid")
    private User user;

    private transient String loginTimeFrom;
    private transient String loginTimeTo;
}
