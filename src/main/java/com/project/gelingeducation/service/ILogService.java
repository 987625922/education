package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Log;
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
     *
     * @param currentPage 页下标
     * @param pageSize    页数
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
     * @param log 日志实体
     */
    @Async
    void save(Log log);

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
     *
     * @param id logId
     */
    void delOneLog(Long id);

    /**
     * 解决了一个日志
     *
     * @param id logId
     */
    void solveOne(Long id);

    /**
     * 把解决的日志标识为未解决
     *
     * @param id logId
     */
    void recurrentOne(Long id);

    /**
     * 获取未解决的日志
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    Object queryNoSolveLog(Integer currentPage, Integer pageSize);

    /**
     * 获取已解决的日志
     *
     * @return
     */
    Object querySolveLog(Integer currentPage, Integer pageSize);

    /**
     * 批量删除
     * @param ids 字符串id
     */
    void delMore(String ids);
}
