package com.project.gelingeducation.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "big_img")
    private String bigImg;
    @Column(name = "remark")
    private String remark;
    @Column(name = "teacher_name")
    private String teacherName;
    @Column(name = "price")
    private double price;

}
