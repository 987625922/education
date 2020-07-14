package com.project.gelingeducation.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 保存文件实体类
 */
@Entity
@Table(name = "local_storage")
@Accessors(chain = true)
@Setter
@Getter
public class LocalStorage implements Serializable {

    private static final long serialVersionUID = -1162095861365288581L;

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
