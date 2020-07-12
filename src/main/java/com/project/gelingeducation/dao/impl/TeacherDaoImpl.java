package com.project.gelingeducation.dao.impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.utils.BeanUtil;
import com.project.gelingeducation.dao.ITeacherDao;
import com.project.gelingeducation.entity.Teacher;
import com.project.gelingeducation.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @Author: LL
 * @Description: 教师的dao
 */
@Repository
public class TeacherDaoImpl extends BaseDao implements ITeacherDao {

    /**
     * 添加教师
     *
     * @param teacher 教师实体类
     * @return
     */
    @Override
    public Teacher insert(Teacher teacher) {
        getSession().save(teacher);
        return teacher;
    }

    /**
     * 更新教师
     *
     * @param teacher 教师实体类
     * @return
     */
    @Override
    public void update(Teacher teacher) {
        Teacher findyTeacher = (Teacher) get(Teacher.class, teacher.getId());
        BeanUtil.copyPropertiesIgnoreNull(teacher, findyTeacher);
    }

    /**
     * 通过id获取教师
     *
     * @param id 教师id
     * @return
     */
    @Override
    public Teacher findById(Long id) {
        return getSession().get(Teacher.class, id);
    }

    /**
     * 获取教师列表
     *
     * @return 全部list
     */
    @Override
    public List<Teacher> queryAll() {
        return getSession().createQuery("FROM Teacher").getResultList();
    }

    /**
     * 获取教师列表
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 返回分页实体类
     */
    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        Session session = getSession();
        String hql = "select count(*) from Teacher";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();
        TypedQuery<User> query = session.createQuery("from Teacher");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数
        long totalPage = (allrows - 1) / pageSize + 1;
        List<User> list = query.getResultList();
        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);
        return pageResult;
    }

    /**
     * 删除教师
     *
     * @param id 教师id
     * @return /
     */
    @Override
    public void delete(Long id) {
        Teacher teacher = findById(id);
        teacher.getVideos().forEach(o -> {
            o.setTeacher(null);
            baseUpdate(o);
        });
        getSession().createQuery("DELETE FROM Teacher WHERE id = " + id)
                .executeUpdate();
    }

    /**
     * 批量删除教师
     *
     * @param ids 1,2,3 格式的专题id
     */
    @Override
    public void delMore(String ids) {
        Query query = getSession().createQuery("FROM Teacher WHERE id IN (" + ids + ")");
        List<Teacher> teachers = query.getResultList();
        for (Teacher teacher : teachers) {
            teacher.getVideos().forEach(o ->{
               o.setTeacher(null);
               baseUpdate(o);
            });
        }
        getSession()
                .createQuery("DELETE FROM Teacher WHERE id IN (" + ids + ")")
                .executeUpdate();
    }

    /**
     * 搜索教师列表
     *
     * @param name        教师名称
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 分页的教师列表
     */
    @Override
    public Object searchCriteria(String name, Integer currentPage, Integer pageSize) {
        Session session = getSession();
        String hql = "SELECT COUNT(*) FROM Teacher WHERE name LIKE '%" + name + "%'";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();
        Query query = session.createQuery("FROM Teacher WHERE name LIKE '%" + name + "%'");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数
        long totalPage = (allrows - 1) / pageSize + 1;
        List<User> list = query.getResultList();
        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);
        return pageResult;
    }
}
