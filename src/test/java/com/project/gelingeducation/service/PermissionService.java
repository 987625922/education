package com.project.gelingeducation.service;

import com.project.gelingeducation.domain.Permission;
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
public class PermissionService {

    @Autowired
    private IPermissionService permissionService;

    @Test
    public void addPermission(){
//        for (int i = 0; i < 10; i++) {
//            Permission permission = new Permission();
//            permission.setName("测试的权限");
//            permission.setUrl("/");
//            permissionService.add(permission);
//        }

        Permission permission = new Permission();
        permission.setName("用户编辑");
        permission.setPerms("user:root");
        permissionService.add(permission);

//        Permission permission = new Permission();
//        permission.setName("用户查看");
//        permission.setPerms("user:view");
//        permissionService.add(permission);
    }
}
