package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ILogService {

    /**
     * 分页查询
     *
     * @return /
     */
    Object queryAll(Integer currentPage, Integer pageSize);


    /**
     * 保存日志数据
     *
     * @param username  用户
     * @param browser   浏览器
     * @param ip        请求IP
     * @param joinPoint
     * @param log       日志实体
     */
    @Async
    void save(String username, String browser, String ip,
              ProceedingJoinPoint joinPoint, Log log);

    /**
     * 根据id查询异常详情
     *
     * @param id 日志ID
     * @return Object
     */
    Log findByErrDetail(Long id);

    /**
     * 导出日志
     *
     * @param logs     待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<Log> logs, HttpServletResponse response) throws IOException;

    /**
     * 删除所有错误日志
     */
    void delAllByError();

    /**
     * 删除所有INFO日志
     */
    void delAllByInfo();
}
