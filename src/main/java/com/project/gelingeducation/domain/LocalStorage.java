package com.project.gelingeducation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "local_storage")
@NoArgsConstructor
public class LocalStorage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 真实文件名
     */
    @Column(name = "name", nullable = false)
    private String realName;

    /**
     * 文件名
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * 后缀
     */
    @Column(name = "name", nullable = false, length = 10)
    private String suffix;
    /**
     * 路径
     */
    @Column(name = "path", nullable = false, length = 10)
    private String path;

    /**
     * 类型
     */
    @Column(name = "path", nullable = false, length = 10)
    private String type;

    /**
     * 大小
     */
    @Column(name = "path", nullable = false, length = 30)
    private String size;


}
