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
 * @author LL
 * @Description: 权限实体类
 */
@Entity
@Table(name = "permission")
@Accessors(chain = true)
@Setter
@Getter
public class Permission implements Serializable {

    private static final long serialVersionUID = 6400268759155522604L;

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 权限名
     */
    @Column(name = "name", length = 24)
    private String name;

    /**
     * 权限备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 权限链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 权限标识
     */
    @Column(name = "perms", length = 20, nullable = false)
    private String perms;

    /**
     * 多对多 权限 - 角色
     */
    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    private Date createDate;

    /**
     * 上次更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    private Date lastUpdateTime;
}
