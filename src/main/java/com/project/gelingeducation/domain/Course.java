package com.project.gelingeducation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 2726599374475533725L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    private double price = 0;

    /**
     * 状态
     * 1为正常，0为禁用
     */
    @Column(name = "status", length = 1)
    private int status = 1;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;

    //修改时间
    @Column(name = "modify_time")
    private Date modifyTime;

    //教师列表
    @ManyToMany(targetEntity = Teacher.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
//    @JsonBackReference //序列化的时候不会出现，反序列化的时候会设置
//    @JsonManagedReference
    private Set<Teacher> teachers = new HashSet<>();

}

