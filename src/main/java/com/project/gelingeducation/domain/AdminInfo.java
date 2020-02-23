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
    //,columnDefinition = "是否是超级管理员"
    @Column(name = "isAdaim",length = 1)
    private int isAdaim;
    @Column(name = "email",length = 32)
    private String eMail;
    //DESCRIPTION
    @Column(name = "ssex",length = 1)
    private int sex;
    //,columnDefinition = "备注"
    @Column(name = "note",length = 100)
    private String note;
    //,columnDefinition = "状态 0锁定 1有效"
    @Column(name = "status",length = 1)
    private String status;
    @Column(name = "create_time",length = 11)
    private String createTime;
    @Column(name = "modify_time",length = 11)
    private String modifyTime;
}
