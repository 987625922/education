package com.project.gelingeducation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
 * @Description: 专题的实体类
 * 备注：
 */
@Entity
@Table(name = "subject")
@Accessors(chain = true)
@Setter
@Getter
public class Subject implements Serializable {

    private static final long serialVersionUID = -4621129159591311477L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 专题名
     */
    @Column(name = "name")
    private String name;

    /**
     * 专题封面
     */
    @Column(name = "big_img")
    private String bigImg;

    /**
     * 专题备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 价格
     */
    @Column(name = "price")
    private Double price;

    /**
     * 专题简介
     */
    @Column(name = "introduction")
    private String introduction;

    /**
     * 多对多 课程
     */
    @ManyToMany(mappedBy = "subjects")
    @JsonBackReference
    private Set<Course> courses = new HashSet<>();

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    private Date createTime;

    /**
     * 上次更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    private Date lastUpdateTime;
}
