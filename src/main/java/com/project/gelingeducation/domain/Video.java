package com.project.gelingeducation.domain;
//
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
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
    @Column(name = "course_id")
    private long courseId;

}
