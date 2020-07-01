package com.project.gelingeducation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
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
     * 多对多 课程
     */
    @ManyToMany(mappedBy = "subjects",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Course> courses = new HashSet<>();
}
