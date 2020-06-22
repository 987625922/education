package com.project.gelingeducation.entity;
//

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@Setter
@Getter
@Entity
@Table(name = "video")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Video implements Serializable {

    private static final long serialVersionUID = 124213019183081702L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "big_img")
    private String bigImg;
    @Column(name = "remark")
    private String remark;
    @Column(name = "video_url")
    private String videoUrl;
    @Column(name = "is_free")
    private Integer isFree;
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "teacher_id",insertable = false,updatable = false)
    private Long teacherId;

    @ManyToOne(targetEntity = Teacher.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id",referencedColumnName = "id") //视频表维护老师的外键
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
