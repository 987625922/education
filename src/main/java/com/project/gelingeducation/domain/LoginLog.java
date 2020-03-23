package com.project.gelingeducation.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login_log")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = -7554456064719088058L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * 用户表的id
     */
    @Column(name = "uid",nullable = false)
    private long uid;

    /**
     * 第一次登录时间
     */
    //默认创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "first_login_time")
    @CreationTimestamp
    private Date firstLoginTime;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;
    /**
     * 登录地点
     */
    @Column(name = "location", length = 48)
    private String location;
    /**
     * 操作系统
     */
    @Column(name = "user_system", length = 14)
    private String userSystem;
    /**
     * 登录浏览器
     */
    @Column(name = "browser", length = 14)
    private String browser;
}
