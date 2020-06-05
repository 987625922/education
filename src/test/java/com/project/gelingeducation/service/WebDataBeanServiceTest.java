package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.WebDataBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@WebAppConfiguration
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class WebDataBeanServiceTest {

    @Autowired
    private IWebDataBeanService webDataBeanService;

    @Test
    public void save() {
        WebDataBean webDataBean = new WebDataBean();
        webDataBean.setAllLoginMun(new Long(11));
        webDataBeanService.save(webDataBean);
    }

    @Test
    public void update() {
        WebDataBean webDataBean = new WebDataBean();
        webDataBean.setId(1);
        webDataBean.setTodayLoginMun(new Long(2));
//        webDataBean.setAllLoginMun(11);
        webDataBeanService.update(webDataBean);
    }

}
