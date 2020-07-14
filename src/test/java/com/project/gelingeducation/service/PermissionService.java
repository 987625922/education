package com.project.gelingeducation.service;

import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@Slf4j
@ActiveProfiles("development")
@WebAppConfiguration
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PermissionService {

    @Autowired
    private IPermissionService permissionService;

    @Test
    public void addPermission() {
        for (int i = 0; i < 10; i++) {
            Permission permission = new Permission();
            permission.setName("测试的权限");
            permission.setUrl("/");
            permission.setPerms("user:root");
            permissionService.add(permission);
        }

//        Permission permission = new Permission();
//        permission.setName("用户编辑");
//        permission.setPerms("user:root");
//        permissionService.add(permission);

//        Permission permission = new Permission();
//        permission.setName("用户查看");
//        permission.setPerms("user:view");
//        permissionService.add(permission);
    }

    @Test
    public void getPermissionListByIds() {
        Long[] ids = {29L, 30L, 31L};
        List<Permission> list = permissionService.getPermissionListByIds(ids);
        for (Permission permission : list) {
            log.debug("==>" + permission.getName());
        }
    }

    @Test
    public void addPersmission() {
        Permission permission = new Permission();
        permission.setName("用户编辑");
        permission.setPerms("user:root");
        Role role = new Role();
        role.setName("123");
        role.setId(170L);
        permission.getRoles().add(role);
        permissionService.add(permission);
    }

}
