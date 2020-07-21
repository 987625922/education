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

import java.util.Date;
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
        Permission permission = new Permission();
        permission.setName("测试的权限");
        permission.setUrl("/");
        permission.setPerms("user:root");
        permissionService.add(permission);
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

    /**
     * 添加最初的权限
     */
    @Test
    public void addPermissionDefault() {
        Date date = new Date();
        Permission permission = new Permission();
        permission.setName("用户查看");
        permission.setPerms("user:view");
        permission.setRemark("普通用户权限");
        permission.setCreateDate(date);
        permission.setLastUpdateTime(date);
        permissionService.add(permission);

        Permission permission1 = new Permission();
        permission1.setName("维护更新");
        permission1.setPerms("operating:update");
        permission1.setRemark("维护更新权限");
        permission1.setCreateDate(date);
        permission1.setLastUpdateTime(date);
        permissionService.add(permission1);

        Permission permission2 = new Permission();
        permission2.setName("维护删除");
        permission2.setPerms("operating:delete");
        permission2.setRemark("维护删除权限");
        permission2.setCreateDate(date);
        permission2.setLastUpdateTime(date);
        permissionService.add(permission2);

        Permission permission3 = new Permission();
        permission3.setName("维护添加");
        permission3.setPerms("operating:add");
        permission3.setRemark("维护添加权限");
        permission3.setCreateDate(date);
        permission3.setLastUpdateTime(date);
        permissionService.add(permission3);

        Permission permission4 = new Permission();
        permission4.setName("管理员添加");
        permission4.setPerms("admin:add");
        permission4.setRemark("管理员添加权限");
        permission4.setCreateDate(date);
        permission4.setLastUpdateTime(date);
        permissionService.add(permission4);

        Permission permission5 = new Permission();
        permission5.setName("管理员删除");
        permission5.setPerms("admin:delete");
        permission5.setRemark("管理员删除权限");
        permission5.setCreateDate(date);
        permission5.setLastUpdateTime(date);
        permissionService.add(permission5);

        Permission permission6 = new Permission();
        permission6.setName("管理员更新");
        permission6.setPerms("admin:upate");
        permission6.setRemark("管理员更新权限");
        permission6.setCreateDate(date);
        permission6.setLastUpdateTime(date);
        permissionService.add(permission6);
    }
}
