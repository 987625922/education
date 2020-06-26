package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.common.utils.HttpUtil;
import com.project.gelingeducation.dao.ILogDao;
import com.project.gelingeducation.entity.Log;
import com.project.gelingeducation.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     * @param username    用户
     * @param browser     浏览器
     * @param ip          请求IP
     * @param description
     * @param methodName
     * @param argValues
     * @param argNames
     * @param params
     * @param log         日志实体
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String username, String browser, String ip, String description,
                     String methodName, Object[] argValues, String[] argNames, String params, Log log) {
        log.setRequestIp(ip);
        log.setAddress(HttpUtil.getCityInfo(log.getRequestIp()));
        log.setMethod(methodName);
        log.setUsername(username);
        log.setParams(params + " }");
        log.setBrowser(browser);
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

    }

}
