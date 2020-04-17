package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

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
//        role.setIsDefault(1);
//        roleService.add(role);

//        Role role = new Role();
//        role.setRemark("普通管理员");
//        role.setName("admin");
//        role.setIsDefault(0);
//        roleService.add(role);
//
//        Role role = new Role();
//        role.setRemark("超级管理员");
//        role.setName("root");
//        role.setIsDefault(0);
//        roleService.add(role);
    }

    @Test
    public void addRoleAndPermission(){
        long[] permissions = {31,32};
        Role role = new Role();
        role.setName("测试");
        roleService.addRole(role,permissions);
    }

    @Test
    public void addPermission() {
        long permissionIds[] = {78};
        roleService.addPermissionByIds(24, permissionIds);
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

    @Test
    public void selByName(){
        List<Role> roles = roleService.selByName("管理员");
        for (int i = 0; i < roles.size(); i++) {
            log.debug("==>"+roles.get(i).getName());
        }
    }

}
