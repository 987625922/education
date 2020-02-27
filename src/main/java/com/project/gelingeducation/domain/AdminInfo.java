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
    @Column(name = "account",nullable=false,length=11)
    private String account;
    @Column(name = "password",nullable=false,length=32)
    private String password;
    @Column(name = "user_name",length = 32)
    private String userName;
    @Column(name = "cover_img")
    private String coverImg;
    //0为超级管理员，1为管理员，2为访客
    @Column(name = "isAdaim",length = 1)
    private int isAdaim;
    @Column(name = "email",length = 32)
    private String eMail;
    //0为保密，1为男，2为女
    @Column(name = "ssex",length = 1)
    private int sex;
    //,columnDefinition = "备注"
    @Column(name = "note",length = 100)
    private String note;
    //,columnDefinition = "状态 0锁定 1有效"
    @Column(name = "status",length = 1)
    private int status;
    @Column(name = "create_time",length = 13)
    private String createTime;
    @Column(name = "modify_time",length = 13)
    private String modifyTime;
}
