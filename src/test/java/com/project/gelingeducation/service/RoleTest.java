package com.project.gelingeducation.service;

import com.project.gelingeducation.common.dto.PageResult;
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

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Slf4j
@WebAppConfiguration
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring/application-data.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleTest {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    /**
     * 添加最初的角色
     */
    @Test
    public void addRoleDefault() {
        List<Permission> permissionServiceList
                = (List<Permission>) permissionService.queryAll(null, null);
        Date date = new Date();
        Role role = new Role();
        role.setRemark("角色：超级管理员");
        role.setName("admin");
        role.setCreateDate(date);
        role.setLastUpdateTime(date);
        HashSet<Permission> permissionHashSet = new HashSet<>();
        permissionServiceList.forEach(p -> permissionHashSet.add(p));
        role.getPermissions().addAll(permissionHashSet);
        roleService.addRole(role);
    }

    @Test
    public void addRole() {
        Role role = new Role();
        role.setRemark("运营维护");
        role.setName("editor");
        roleService.addRole(role);

//        Role role = new Role();
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
//        roleService.addRole(role);
    }

    @Test
    public void addRoleAndPermission() {
//        Long[] permissions = {31L, 32L};
//        Role role = new Role();
//        role.setName("测试");
//        roleService.addRole(role, permissions);
    }

    @Test
    public void addPermission() {
        Long permissionIds[] = {78L};
        roleService.addPermissionByIds(24L, permissionIds);
    }

    @Test
    public void list() {
        PageResult pageResult = (PageResult) roleService.queryAll(1, 3);
        for (Role role : (List<Role>) pageResult.getLists()) {
            log.info("==>" + role);
        }
    }

    @Test
    public void findyId() {
        Role role = roleService.findByRole(10L);
        log.debug("==>" + role);
    }

    @Test
    public void selByName() throws UnsupportedEncodingException {
        List<Role> roles = roleService.selByName("d");
        for (int i = 0; i < roles.size(); i++) {
            log.debug("==>" + roles.get(i).getName());
        }
    }

    @Test
    public void getRoleByIdForPermission() {
        List<Permission> permissions =
                roleService.getRoleByIdForPermission(24L);
        for (Permission permission : permissions) {
            log.debug("==>" + permission.getName());
        }
    }
}
