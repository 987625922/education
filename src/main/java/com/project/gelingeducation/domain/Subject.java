package com.project.gelingeducation.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "big_img")
    private String bigImg;
}
