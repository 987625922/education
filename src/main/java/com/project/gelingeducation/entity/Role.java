package com.project.gelingeducation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: LL
 * @Description: 角色的实体类
 * 备注：
 */
@Entity
@Table(name = "role")
@Accessors(chain = true)
@Setter
@Getter
public class Role implements Serializable {

    private static final long serialVersionUID = 9205096691157148000L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 角色名
     */
    @Column(name = "name", length = 24)
    private String name;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createDate;

    /**
     * 上次更新时间
     */
//    @Column(name = "last_update_time", nullable = false)
//    private Date lastUpdateTime;

    /**
     * 角色备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 是否是用户创建时的默认身份
     * 0为不是默认注册时的身份,1为默认
     */
    @Column(name = "is_default", nullable = false)
    private Integer isDefault = 0;

    /**
     * 一对多 用户
     */
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    /**
     * 多对多 权限
     */
    @ManyToMany(targetEntity = Permission.class
            , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions = new HashSet<>();
}
