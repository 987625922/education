package com.project.gelingeducation.service.Impl;

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

@Service
@Transactional(readOnly = true)
public class LogServiceImpl implements ILogService {

    @Autowired
    private ILogDao logDao;

    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return logDao.queryAll(currentPage, pageSize);
        } else {
            return logDao.queryAll();
        }
    }

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

    @Override
    public Log findByErrDetail(Long id) {
        return logDao.findByErrDetail(id);
    }

    @Override
    public void download(List<Log> logs, HttpServletResponse response) throws IOException {
        logDao.download(logs, response);
    }

    @Transactional
    @Override
    public void delAllByError() {
        logDao.delAllByError();
    }

    @Transactional
    @Override
    public void delAllByInfo() {
        logDao.delAllByInfo();
    }

}
