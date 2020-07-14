package com.project.gelingeducation.dao.impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.utils.BeanUtil;
import com.project.gelingeducation.dao.IRoleDao;
import com.project.gelingeducation.entity.Permission;
import com.project.gelingeducation.entity.Role;
import com.project.gelingeducation.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Repository
public class RoleDaoImpl extends BaseDao implements IRoleDao {


    @Override
    public void insert(Role role) {
        getSession().save(role);
    }

    @Override
    public Role findById(Long id) {
        return getSession().get(Role.class, id);
    }

    @Override
    public List<Role> queryAll() {
        return getSession().createQuery("from Role").list();
    }

    @Override
    public void delRoleById(Long id) {
        Session session = getSession();
        session.createQuery("DELETE FROM Role WHERE id =" + id)
                .executeUpdate();
    }

    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Role";//此处的Product是对象
        Query queryCount = session.createQuery(hql);
        Long allrows = (Long) queryCount.uniqueResult();

        TypedQuery<User> query = session.createQuery("from Role");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        Long totalPage = (allrows - 1) / pageSize + 1;
        List<User> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);
        return pageResult;
    }


    @Override
    public Role findDefault() {
        Query query = getSession().createQuery("from Role where is_default=?0");
        query.setParameter(0, 1);
        return (Role) query.uniqueResult();
    }

    @Override
    public List selByName(String name) {
        return getSession().createQuery("FROM Role WHERE name LIKE '%" + name + "%'").getResultList();
    }

    @Override
    public void delByIds(String ids) {
        Query query = getSession().createQuery
                ("DELETE FROM Role WHERE id in(" + ids + ")");
        query.executeUpdate();
    }

    @Override
    public List<Permission> getRoleByIdForPermission(Long id) {
        Session session = getSession();
        Role role = session.get(Role.class, id);
        Set<Permission> permissions = role.getPermissions();
        List<Permission> permissionList = new ArrayList<>();
        for (Permission permission : permissions) {
            permissionList.add(permission);
        }
        return permissionList;
    }

    @Override
    public Role getRoleByUserId(Long userId) {
        return getSession().get(User.class, userId).getRole();
    }

    @Override
    public void update(Role role) {
        Session session = getSession();
        Role findRole = session.get(Role.class, role.getId());
        BeanUtil.copyPropertiesIgnoreNull(role, findRole);
    }

}
