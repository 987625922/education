package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.utils.HttpUtil;
import com.project.gelingeducation.dao.ILoginLogDao;
import com.project.gelingeducation.dao.IWebDataBeanDao;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.domain.WebDataBean;
import com.project.gelingeducation.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LoginLogServiceImpl implements ILoginLogService {

    @Autowired
    private ILoginLogDao loginLogDao;
    @Autowired
    private IWebDataBeanDao webDataBeanDao;

    @Override
    public void insert(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        String ip = HttpUtil.getCityInfo(HttpUtil.getIp(HttpUtil.getHttpServletRequest()));
        loginLog.setIp(ip);
        loginLog.setLocation(HttpUtil.getCityInfo(ip));
        loginLogDao.insert(loginLog);
    }

    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return loginLogDao.queryAll(currentPage, pageSize);
        } else {
            return loginLogDao.queryAll();
        }
    }

    @Override
    @Transactional
    public LoginLog getByUserId(Long uid) {
        return loginLogDao.getByUid(uid);
    }

    @Transactional
    @Override
    public void saveOrUpdateLoginLogByUid(User user) {
        Optional<LoginLog> optionalLoginLog = Optional.ofNullable(user.getLoginLog());
        HttpServletRequest servletRequest = HttpUtil.getHttpServletRequest();
        String ip = HttpUtil.getIp(servletRequest);
        optionalLoginLog.ifPresent(loginLog -> {
            loginLog.setLastLoginTime(loginLog.getLoginTime());
            loginLog.setLocation(HttpUtil.getCityInfo(ip));
            loginLog.setBrowser(HttpUtil.getBrowser(servletRequest));
            loginLog.setLoginTime(new Date());
            loginLog.setUserSystem(HttpUtil.getOsName(servletRequest));
            loginLog.setIp(ip);
        });
        optionalLoginLog.orElseGet(() -> {
            LoginLog loginLog = new LoginLog();
            loginLog.setUser(user);
            loginLog.setLoginTime(new Date());
            loginLog.setBrowser(HttpUtil.getBrowser(servletRequest));
            loginLog.setIp(ip);
            loginLog.setLocation(HttpUtil.getCityInfo(ip));
            loginLog.setUserSystem(HttpUtil.getOsName(servletRequest));
            loginLogDao.insert(loginLog);
            return loginLog;
        });
        //全局登录数据统计
        Optional<WebDataBean> optionalWebData = Optional.ofNullable(webDataBeanDao.getOnlyData());
        optionalWebData.ifPresent(webDataBean -> {
            webDataBean.setAllLoginMun(webDataBean.getAllLoginMun() + 1);
//            webDataBean.setTodayLoginMun();
        });
        optionalWebData.orElseGet(() -> {
            WebDataBean webDataBean = new WebDataBean();
            webDataBeanDao.save(webDataBean);
            return webDataBean;
        });
    }


}
