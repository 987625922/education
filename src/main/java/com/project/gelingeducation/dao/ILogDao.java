package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.Log;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ILogDao {

    /**
     * 分页查询
     * @return /
     */
    PageResult queryAll(Integer currentPage, Integer pageSize);

    /**
     * 所有查询
     * @return /
     */
    List queryAll();

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
    Log findByErrDetail(Long id);

    /**
     * 导出日志
     * @param logs 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<Log> logs, HttpServletResponse response) throws IOException;

    /**
     * 删除所有error日志
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
