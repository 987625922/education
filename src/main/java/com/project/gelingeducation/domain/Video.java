package com.project.gelingeducation.domain;
//
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "big_img")
    private String bigImg;
    @Column(name = "remark")
    private String remark;
    @Column(name = "video_url")
    private String videoUrl;
    @Column(name = "teacher_name")
    private String teacherName;
    @Column(name = "is_free")
    private int isFree;

}
