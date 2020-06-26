package com.project.gelingeducation.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: LL
 * @Description: 错误日志的entity
 * 备注：
 *      1.@NoArgsConstructor 因为创建了一个非空的构造器，这里创建一个空的，
 *      不然hibernate搜索会报错
 */
@Entity
@Table(name = "sys_log")
@Accessors(chain = true)
@Setter
@Getter
@NoArgsConstructor
public class Log implements Serializable {

    private static final long serialVersionUID = 7913187238778019641L;

    public Log(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 描述
     */
    private String description;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private String params;

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 请求ip
     */
    private String requestIp;

    /**
     * 地址
     */
    private String address;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 请求耗时
     */
    private Long time;

    /**
     * 异常详细
     */
    @Column(columnDefinition = "text")
    private String exceptionDetail;

    /**
     * 是否解决
     * 1为解决，其他的为未解决
     */
    @Column()
    private Integer isSolve;

    /**
     * 创建日期
     */
    @CreationTimestamp
    private Timestamp createTime;
}
