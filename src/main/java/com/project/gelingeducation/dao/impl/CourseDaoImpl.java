package com.project.gelingeducation.dao.impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.utils.BeanUtil;
import com.project.gelingeducation.common.utils.CollectUtil;
import com.project.gelingeducation.dao.ICourseDao;
import com.project.gelingeducation.entity.Course;
import com.project.gelingeducation.entity.Subject;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @Author: LL
 * @Description: 课程的dao
 */
@Repository
public class CourseDaoImpl extends BaseDao implements ICourseDao {

    /**
     * 搜索所有的课程
     *
     * @return list
     */
    @Override
    public Object queryAll() {
        return getSession().createQuery("from Course").getResultList();
    }

    /**
     * 根据id获取课程
     *
     * @param id 课程id
     * @return 课程实体类
     */
    @Override
    public Course findById(Long id) {
        return getSession().get(Course.class, id);
    }

    /**
     * 添加课程
     *
     * @param course 课程实体类
     * @return 课程id
     */
    @Override
    public Long insert(Course course) {
        getSession().save(course);
        return course.getId();
    }

    /**
     * 根据id删除课程
     *
     * @param id 视频id
     */
    @Override
    public void delect(Long id) {
        getSession()
                .createQuery("DELETE From Course WHERE id = " + id)
                .executeUpdate();
    }

    /**
     * 更新课程
     *
     * @param course 课程实体
     */
    @Override
    public void update(Course course) {
        Session session = getSession();
        Course findCourse = session.get(Course.class, course.getId());
        BeanUtil.copyPropertiesIgnoreNull(course, findCourse);
        session.update(findCourse);
    }

    /**
     * 根据分页条件获取分页实体类
     *
     * @param currentPage 页下标
     * @param pageSize    页数
     * @return 分页实体类
     */
    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
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

    /**
     * 批量删除课程
     *
     * @param ids 格式为1,2,3的课程id
     */
    @Override
    public void delMore(String ids) {
        Query query = getSession().createQuery(
                "DELETE FROM Course WHERE id in(" + ids + ")");
        query.executeUpdate();
    }

    /**
     * 根据条件搜索课程分页信息
     *
     * @param name        课程名
     * @param status      课程状态
     * @param startPrice  课程搜索开始的价格
     * @param endPrice    课程搜索结束的价格
     * @param teacherId   教师id
     * @param currentPage 页面下标
     * @param pageSize    页数
     * @return 分页实体类
     */
    @Override
    public Object selByNameOrStatusOrPriceOrTeacher(String name, Integer status,
                                                    Double startPrice, Double endPrice,
                                                    Long teacherId, Integer currentPage,
                                                    Integer pageSize) {
        Session session = getSession();
        StringBuffer hql = new StringBuffer("FROM Course AS course");
        if (teacherId != null) {
            hql.append(" INNER JOIN FETCH course.videos.teacher AS teacher ");
        }
        hql.append(" WHERE 1=1");
        if (teacherId != null) {
            hql.append(" AND teacher.id = " + teacherId);
        }
        if (status != null) {
            hql.append(" AND course.status = " + status);
        }
        if (startPrice != null && endPrice != null) {
            hql.append(" AND course.price BETWEEN " + startPrice + " AND " + endPrice);
        }
        if (!StringUtils.isEmpty(name)) {
            hql.append(" AND course.name LIKE '%" + name + "%'");
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

    /**
     * 通过专题id获取课程列表
     *
     * @param subjectId 专题id
     * @return
     */
    @Override
    public Object getCourseListBySubjectId(Long subjectId) {
        Subject subject = (Subject) get(Subject.class, subjectId);
        return CollectUtil.setToList(subject.getCourses());
    }
}
