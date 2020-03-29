package com.project.gelingeducation.dao;

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
public class RoleDaoTest {

    @Autowired
    IRoleDao roleDao;

    @Test
    public void testInsert() {
        Role role = new Role();
        role.setName("11");
        role.setRemark("111");
        roleDao.insert(role);

    }

}
