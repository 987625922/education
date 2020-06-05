package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.common.utils.HttpUtil;
import com.project.gelingeducation.common.utils.IPUtil;
import com.project.gelingeducation.dao.ILoginLogDao;
import com.project.gelingeducation.dao.IWebDataBeanDao;
import com.project.gelingeducation.domain.LoginLog;
import com.project.gelingeducation.domain.WebDataBean;
import com.project.gelingeducation.service.IWebDataBeanService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class WebDataBeanServiceImpl implements IWebDataBeanService {
    @Autowired
    private IWebDataBeanDao webDataBeanDao;
    @Autowired
    private ILoginLogDao loginLogDao;

    @Override
    public WebDataBean findById(Integer id) {
        return webDataBeanDao.findById(id);
    }

    @Override
    public void save(WebDataBean webDataBean) {
        webDataBeanDao.save(webDataBean);
    }

    @Override
    public void update(WebDataBean webDataBean) {
        webDataBeanDao.update(webDataBean);
    }

    @Override
    public void userLogin() {
        String ip = IPUtil.getIpAddr(HttpUtil.getHttpServletRequest());
        WebDataBean webDataBean = webDataBeanDao.getOnlyData();
        if (webDataBean == null) {
            webDataBean = new WebDataBean();
            webDataBeanDao.save(webDataBean);
        }
        List<LoginLog> loginLogs = loginLogDao.getLoginLogByIp(ip);
        Date loginDate = new Date();
        for (LoginLog loginLog : loginLogs) {
            boolean isSameDate = DateUtils.isSameDay(loginDate, loginLog.getLoginTime());
            if (isSameDate) {
                if (loginLog.getLastLoginTime() == null ||
                        DateUtils.isSameDay(loginLog.getLastLoginTime(), loginLog.getLoginTime())) {
                    webDataBean.setTodayLoginIpMun(webDataBean.getTodayLoginIpMun() + 1);
                    break;
                }
            }
        }
        webDataBean.setTodayLoginMun(webDataBean.getTodayLoginMun() + 1);
        webDataBean.setAllLoginMun(webDataBean.getAllLoginMun() + 1);
        webDataBean.setTodayLoginTime(loginDate);
    }

    @Override
    public WebDataBean getWebDataBean() {
        return webDataBeanDao.getOnlyData();
    }


}
