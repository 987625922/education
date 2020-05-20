package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ILogDao {

    /**
     * 分页查询
     * @return /
     */
    Object queryAll(int currentPage, int pageSize);


    /**
     * 保存日志数据
     * @param log 日志实体
     */
    @Async
    void save(Log log);

    /**
     * 根据id查询异常详情
     * @param id 日志ID
     * @return Object
     */
    Object findByErrDetail(Long id);

    /**
     * 导出日志
     * @param logs 待导出的数据
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
