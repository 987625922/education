package com.project.gelingeducation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1595117855604940548L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 教师名
     */
    @Column(name = "name", length = 50)
    private String name;
    /**
     * 头像
     */
    @Column(name = "big_img")
    private String bigImg;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @CreationTimestamp
    private Date createTime;

    //修改时间
    @Column(name = "modify_time")
    private Date modifyTime;

    @ManyToMany(mappedBy = "teachers",fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Course> courses = new HashSet<>();

    //mappdBy 映射的是哪一个属性
    @OneToMany(targetEntity = Video.class,mappedBy = "teacher",fetch = FetchType.EAGER)
    private Set<Video> videos = new HashSet<>();


    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bigImg='" + bigImg + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
