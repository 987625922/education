package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.IPermissionDao;
import com.project.gelingeducation.domain.Permission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionDaoImpl extends BaseDao implements IPermissionDao {


    @Override
    public void insertPermission(Permission permission) {
        getSession().save(permission);
    }

    @Override
    public Permission getById(Long id) {
        return getSession().get(Permission.class,id);
    }

    @Override
    public List list() {
        return getSession().createQuery("from Permission").list();
    }

    @Override
    public List<Permission> getPermissionListByIds(Long[] ids) {
        String sql = "";
        for (int i = 0; i < ids.length; i++) {
            if (i == 0) {
                sql = sql + ids[i];
            } else {
                sql = sql + "," + ids[i];
            }
        }
        Query query = getSession().createQuery("from Permission WHERE id in (" + sql + ")");
        return query.list();
    }


}
