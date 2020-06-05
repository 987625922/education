package com.project.gelingeducation.dao;

import com.project.gelingeducation.domain.WebDataBean;

public interface IWebDataBeanDao {

    WebDataBean findById(Integer id);

    void save(WebDataBean webDataBean);

    void update(WebDataBean webDataBean);

    WebDataBean getOnlyData();
}
