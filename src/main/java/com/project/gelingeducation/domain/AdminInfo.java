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
    @Column(name = "user_name",length = 32)
    private String userName;
    @Column(name = "cover_img")
    private String coverImg;
    @Column(name = "isAdaim",length = 1)
    private int isAdaim;
    @Column(name = "email",length = 32)
    private String EMail;
    @Column(name = "sex",length = 1,columnDefinition = "1为男，2为女")
    private int isMale;
    @Column(name = "note",length = 100)
    private String note;

}
