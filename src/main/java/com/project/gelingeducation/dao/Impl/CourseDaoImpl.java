package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.utils.BeanUtils;
import com.project.gelingeducation.dao.ICourseDao;
import com.project.gelingeducation.domain.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class CourseDaoImpl extends BaseDao implements ICourseDao {

    @Override
    public List queryAll() {
        return getSession().createQuery("from Course").getResultList();
    }

    @Override
    public Course findById(Long id) {
        return getSession().get(Course.class, id);
    }

    @Override
    public Long insert(Course course) {
        getSession().save(course);
        return course.getId();
    }

    @Override
    public void delect(Long id) {
        getSession().createQuery("DELETE From Course WHERE id = " + id).executeUpdate();
    }

    @Override
    public void update(Course course) {
        Session session = getSession();
        Course findCourse = session.get(Course.class, course.getId());
        BeanUtils.copyPropertiesIgnoreNull(course, findCourse);
        session.update(findCourse);
    }


    @Override
    public PageResult queryAll(Integer currentPage, Integer pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Course";
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<Course> query = session.createQuery("from Course");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        long totalPage = (allrows - 1) / pageSize + 1;
        List<Course> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }


    @Override
    public void delMore(String ids) {
        Query query = getSession().createQuery(
                "DELETE FROM Course WHERE id in(" + ids + ")");
        query.executeUpdate();
    }


    @Override
    public PageResult selByNameOrStatusOrPriceOrTeacher(String name, Integer status,
                                                        Double startPrice, Double endPrice,
                                                        Long teacherId, Integer currentPage,
                                                        Integer pageSize) {
        Session session = getSession();

        StringBuffer hql = new StringBuffer("from Course as course");

        if (teacherId != null) {
            hql.append(" inner join fetch course.teachers as teacher ");
        }

        hql.append(" where 1=1");

        if (teacherId != null) {
            hql.append(" AND teacher.id = " + teacherId);
        }

        if (status != null) {
            hql.append(" AND course.status = " + status);
        }
        if (startPrice != null && endPrice != null) {
            hql.append(" AND course.price BETWEEN " + startPrice + " AND " + endPrice);
        }
        if (!name.isEmpty()) {
            hql.append(" AND course.name LIKE '%" + name + "%'");
        }

        Query query = session.createQuery(hql.toString());

        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(currentPage * pageSize);//得到每页的记录数
        List<Course> list = query.getResultList();

        hql.insert(0, "select count(*) ");


        Query queryCount = session.createQuery(hql.toString().replace("fetch", ""));
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
