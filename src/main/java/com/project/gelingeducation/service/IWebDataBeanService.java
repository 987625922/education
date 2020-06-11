package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.domain.WebDataBean;

public interface IWebDataBeanService {

    WebDataBean findById(Integer id);

    void save(WebDataBean webDataBean);

    void update(WebDataBean webDataBean);

    Object login(User user);

    WebDataBean getWebDataBean();
}
