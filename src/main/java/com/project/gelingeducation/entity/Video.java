package com.project.gelingeducation.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: LL
 * @Description: 视频的实体类
 */
@Accessors(chain = true)
@Setter
@Getter
@Entity
@Table(name = "video")
public class Video implements Serializable {

    private static final long serialVersionUID = 124213019183081702L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 视频名
     */
    @Column(name = "name")
    private String name;

    /**
     * 视频封面
     */
    @Column(name = "big_img")
    private String bigImg;

    /**
     * 视频备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 视频链接
     */
    @Column(name = "video_url")
    private String videoUrl;

    /**
     * 多对一老师id
     */
    @Column(name = "teacher_id", insertable = false, updatable = false)
    private Long teacherId;

    /**
     * 多对一老师表
     * <p>
     * 视频表维护老师的外键
     */
    @ManyToOne(targetEntity = Teacher.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    /**
     * 多对多 课程
     */
    @ManyToMany(mappedBy = "videos", fetch = FetchType.EAGER)
    private Set<Course> courses = new HashSet<>();

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, updatable = false)
    private Date createTime;

    /**
     * 上次更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false)
    private Date lastUpdateTime;
}
