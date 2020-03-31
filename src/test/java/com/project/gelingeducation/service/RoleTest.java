package com.project.gelingeducation.service;

import com.project.gelingeducation.dao.IRoleDao;
import com.project.gelingeducation.domain.Role;
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
public class RoleTest {

    @Autowired
    private IRoleDao roleDao;

    @Test
    public void addRole() {
        Role role = new Role();
        role.setRemark("普通管理员");
        role.setName("admin");
        roleDao.insert(role);
    }
}
