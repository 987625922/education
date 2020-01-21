package com.project.gelingeducation.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin")
public class AdminInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "phone")
    private int phone;
    @Column(name = "password")
    private String password;
    @Column(name = "cover_img")
    private String coverImg;

}
