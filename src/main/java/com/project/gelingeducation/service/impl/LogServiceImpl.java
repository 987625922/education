package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.dao.ILogDao;
import com.project.gelingeducation.entity.Log;
import com.project.gelingeducation.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: LL
 * @Description: 错误日志的entity
 * 备注：
 */
@Service
@Transactional(readOnly = true)
public class LogServiceImpl implements ILogService {

    @Autowired
    private ILogDao logDao;

    /**
     * 分页和全部搜索
     *
     * @param currentPage 页下标
     * @param pageSize    页数
     * @return Object
     */
    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return logDao.queryAll(currentPage, pageSize);
        } else {
            return logDao.queryAll();
        }
    }

    /**
     * 保存日志
     *
     * @param log 日志实体
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Log log) {
        log.setCreateTime(new Date());
        logDao.save(log);
    }

    /**
     * 根据id搜索错误日志
     *
     * @param id 日志ID
     * @return
     */
    @Override
    public Log findByErrDetail(Long id) {
        return logDao.findByErrDetail(id);
    }

    /**
     * 导出数据
     *
     * @param logs     待导出的数据
     * @param response /
     * @throws IOException
     */
    @Override
    public void download(List<Log> logs, HttpServletResponse response) throws IOException {
        logDao.download(logs, response);
    }

    /**
     * 删除所有error日志
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delAllByError() {
        logDao.delAllByError();
    }

    /**
     * 删除所有info级别日志
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delAllByInfo() {
        logDao.delAllByInfo();
    }

    /**
     * 根据id删除一个日志
     *
     * @param id logId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delOneLog(Long id) {
        logDao.delOneLog(id);
    }

    /**
     * 解决了一个日志
     *
     * @param id logId
     */
    @Override
    public void solveOne(Long id) {
        logDao.solveOne(id);
    }

    /**
     * 把解决的日志标识为未解决
     *
     * @param id logId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void recurrentOne(Long id) {
        logDao.recurrentOne(id);
    }

    /**
     * 获取未解决的日志
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object queryNoSolveLog(Integer currentPage, Integer pageSize) {
        return logDao.queryNoSolveLog(currentPage, pageSize);
    }

    /**
     * 获取已解决的日志
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public Object querySolveLog(Integer currentPage, Integer pageSize) {
        return logDao.querySolveLog(currentPage, pageSize);
    }

    /**
     *
     * @param ids 字符串id
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delMore(String ids) {
        logDao.delMore(ids);
    }
}
