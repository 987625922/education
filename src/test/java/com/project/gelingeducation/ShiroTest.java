package com.project.gelingeducation;

import com.project.gelingeducation.common.authentication.ShiroConfig;
import com.project.gelingeducation.common.config.HibernateConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateConfig.class, ShiroConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ShiroTest {



}
