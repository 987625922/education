package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: LL
 * @Description: 错误日志的service接口
 */
public interface ILogService {

    /**
     * 分页查询
     * @param currentPage 页下标
     * @param pageSize 页数
     * @return 带Log的list的@PageResult
     */
    Object queryAll(Integer currentPage, Integer pageSize);

    /**
     * 根据id查询异常详情
     *
     * @param id 日志ID
     * @return @Log
     */
    Log findByErrDetail(Long id);

    /**
     * 保存日志数据
     *
     * @param username  用户
     * @param browser   浏览器
     * @param ip        请求IP
     * @param description
     * @param mehodName
     * @param argValues
     * @param argNames
     * @param log       日志实体
     */
    void save(String username, String browser, String ip,
              String description, String mehodName, Object[] argValues
            , String[] argNames,String params, Log log);

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

    /**
     * 根据id删除
     * @param id logId
     */
    void delOneLog(Long id);
}
