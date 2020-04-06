package com.project.gelingeducation.service;

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
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @Test
    public void addRole() {
//        Role role = new Role();
//        role.setRemark("运营维护");
//        role.setName("editor");
//        roleService.add(role);

//        Role role = new Role();
//        role.setRemark("普通管理员");
//        role.setName("admin");
//        roleService.add(role);

        Role role = new Role();
        role.setRemark("超级管理员");
        role.setName("root");
        roleService.add(role);
    }

    @Test
    public void addPermission() {
        long permissionIds[] = {24};
        roleService.addPermissionByIds(17, permissionIds);
    }

    @Test
    public void list() {
        for (Role role : roleService.list()) {
            log.debug("==>" + role);
        }
    }

    @Test
    public void findyId() {
        Role role = roleService.findByRole(10);
        log.debug("==>" + role);
    }


}
