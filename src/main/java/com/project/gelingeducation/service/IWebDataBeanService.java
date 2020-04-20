package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.WebDataBean;

public interface IWebDataBeanService {

    WebDataBean findById(int id);

    void save(WebDataBean webDataBean);

    void update(WebDataBean webDataBean);

    void userLogin();
}
