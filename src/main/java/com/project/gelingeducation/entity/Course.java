package com.project.gelingeducation.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: LL
 * @Description: 课程的实体类
 * 备注：
 */
@Entity
@Table(name = "course")
@Accessors(chain = true)
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Course implements Serializable {

    private static final long serialVersionUID = 2726599374475533725L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 课程名
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    /**
     * 封面
     */
    @Column(name = "big_img")
    private String bigImg;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
    /**
     * 价格
     */
    @Column(name = "price")
    private Double price;

    /**
     * 状态
     * 1为正常，0为禁用
     */
    @Column(name = "status", length = 1)
    private Integer status = 1;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreatedDate
    private Date createTime;

    /**
     * 上次更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    @LastModifiedDate
    private Date lastUpdateTime;

    /**
     * 教师列表
     */
    @ManyToMany(targetEntity = Teacher.class,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany(targetEntity = Subject.class,
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_course_subject",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<>();
}


