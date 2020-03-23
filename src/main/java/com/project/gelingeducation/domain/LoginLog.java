package com.project.gelingeducation.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private long id;
    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;
    /**
     * 登录地点
     */
    @Column(name = "location",length = 48)
    private String location;
    /**
     * 操作系统
     */
    @Column(name = "system",length = 14)
    private String system;
    /**
     * 登录浏览器
     */
    @Column(name = "browser",length = 14)
    private String browser;
}
