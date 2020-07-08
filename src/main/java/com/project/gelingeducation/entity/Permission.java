package com.project.gelingeducation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(name = "name", length = 24)
    private String name;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createDate;

    @Column(name = "remark")
    private String remark;

    @Column(name = "url")
    private String url;

    /**
     * 权限标识
     */
    @Column(name = "perms", length = 12, nullable = false)
    private String perms;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

}
