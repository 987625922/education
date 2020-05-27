package com.project.gelingeducation.domain;
//

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn(name = "teacher_id") //视频表维护老师的外键
    private Teacher teacher;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    @CreatedDate
    private Date createTime;

    /**
     * 上次更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    @LastModifiedDate
    private Date lastUpdateTime;


}
