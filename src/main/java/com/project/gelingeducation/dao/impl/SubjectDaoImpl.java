package com.project.gelingeducation.dao.impl;

import com.google.common.base.Strings;
import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.utils.BeanUtil;
import com.project.gelingeducation.dao.ISubjectDao;
import com.project.gelingeducation.entity.Course;
import com.project.gelingeducation.entity.Subject;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @Author: LL
 * @Description: 专题实体类的dao
 */
@Slf4j
@Repository
public class SubjectDaoImpl extends BaseDao implements ISubjectDao {

    /**
     * 分页搜索专题
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return
     */
    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Subject";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<Subject> query = session.createQuery("from Subject");
        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(pageSize);

        long totalPage = (allrows - 1) / pageSize + 1;
        List<Subject> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }

    /**
     * 搜索所有的专题
     *
     * @return
     */
    @Override
    public List<Subject> queryAll() {
        return getSession().createQuery("FROM Subject").list();
    }

    /**
     * 根据id获取专题
     *
     * @param id 专题id
     * @return
     */
    @Override
    public Subject findById(Long id) {
        return getSession().get(Subject.class, id);
    }

    /**
     * 添加专题
     *
     * @param subject 专题实体类
     * @return
     */
    @Override
    public Subject insert(Subject subject) {
        Session session = getSession();
        session.save(subject);
        for (Course course : subject.getCourses()) {
            course.getSubjects().clear();
            course.getSubjects().add(subject);
        }
        return subject;
    }

    /**
     * 删除专题
     *
     * @param id 专题id
     */
    @Override
    public void delect(Long id) {
        getSession().createQuery(
                "DELETE From Subject WHERE id = " + id).executeUpdate();
    }

    /**
     * 更新专题
     *
     * @param subject 专题实体类
     */
    @Override
    public void update(Subject subject) {
        Subject findSubject = (Subject) get(Subject.class, subject.getId());
        for (Course course : findSubject.getCourses()) {
            course.getSubjects().clear();
        }
        BeanUtil.copyPropertiesIgnoreNull(subject, findSubject);
        baseUpdate(findSubject);
        for (Course course : findSubject.getCourses()) {
            course.getSubjects().add(findSubject);
        }
    }

    /**
     * 批量删除专题
     *
     * @param ids 视频id 格式为 1,2,3
     */
    @Override
    public void delMore(String ids) {
        getSession().createQuery(
                "DELETE FROM Subject WHERE id in(" + ids + ")")
                .executeUpdate();
    }

    /**
     * 条件搜索
     *
     * @param name        专题名称
     * @param courseIds   1,2,3 格式的字符串id
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 分页实体类
     */
    @Override
    public Object searchCriteria(String name, String courseIds, Integer currentPage,
                                 Integer pageSize) {
        Session session = getSession();
        StringBuilder hql = new StringBuilder("FROM Subject AS s");
        if (!Strings.isNullOrEmpty(courseIds)) {
            hql.append(" INNER JOIN FETCH s.courses AS c");
        }
        hql.append(" WHERE 1=1");
        if (!Strings.isNullOrEmpty(name)) {
            hql.append(" AND s.name LIKE %" + name + "%");
        }
        if (!Strings.isNullOrEmpty(courseIds)) {
            hql.append(" AND c.id in (" + courseIds + ")");
        }

        Query query = session.createQuery(hql.toString());
        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(currentPage * pageSize);
        List<Course> list = query.getResultList();
        hql.insert(0, "select count(*) ");
        Query queryCount = session.createQuery(hql.toString().replace("FETCH", ""));
        Long allrows = (Long) queryCount.uniqueResult();
        Long totalPage = (allrows - 1) / pageSize + 1;
        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);
        return pageResult;
    }
}
