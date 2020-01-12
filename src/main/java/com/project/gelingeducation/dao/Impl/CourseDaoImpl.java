package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.CourseDao;
import com.project.gelingeducation.domain.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Course> findAll() {
        TypedQuery<Course> query = getSession().createQuery("from course");
        return query.getResultList();
    }

    @Override
    public Course findById(long id) {
        Course course = getSession().get(Course.class, id);
        return course;
    }

    @Override
    public long insert(Course course) {
        getSession().save(course);
        return course.getId();
    }

    @Override
    public void delect(long id) {
        getSession().delete(getSession().get(Course.class, id));
    }

    @Override
    public void update(Course course) {
        getSession().update(course);
    }
}
