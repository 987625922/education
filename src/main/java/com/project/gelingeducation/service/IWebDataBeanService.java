package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.entity.WebDataBean;

public interface IWebDataBeanService {

    Object login(User user);

    WebDataBean getWebDataBean();

    void addLoginMun();

    void clearTodayLoginMun();
}
