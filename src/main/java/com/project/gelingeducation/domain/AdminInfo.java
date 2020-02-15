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
    @Column(name = "phone",nullable=false,length=11)
    private String phone;
    @Column(name = "password",nullable=false,length=32)
    private String password;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "cover_img")
    private String coverImg;

}
