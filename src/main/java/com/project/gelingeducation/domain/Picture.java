package com.project.gelingeducation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "picture")
@NoArgsConstructor
public class Picture {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  文件名
    @Column(name = "filename", nullable = false, length = 50)
    private String filename;

    //图片url
    @Column(name = "filename", nullable = false)
    private String url;

    //图片大小
    @Column(name = "filename", nullable = false)
    private String size;

    //图片高
    @Column(name = "filename", nullable = false)
    private String height;

    //图片宽
    @Column(name = "filename", nullable = false)
    private String width;

    @CreationTimestamp
    //创建时间
    @Column(name = "filename", nullable = false)
    private Timestamp createTime;

    /**
     * 用于检测文件是否重复
     */
    @Column(name = "md5Code", nullable = false)
    private String md5Code;


}
