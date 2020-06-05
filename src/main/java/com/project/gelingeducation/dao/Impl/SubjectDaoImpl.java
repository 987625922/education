package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.ISubjectDao;
import com.project.gelingeducation.domain.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class SubjectDaoImpl implements ISubjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List findAll() {
        return getSession().createQuery("from Subject").getResultList();
    }

    @Override
    public Subject findById(Long id) {
        return getSession().get(Subject.class, id);
    }

    @Override
    public long insert(Subject subject) {
        getSession().save(subject);
        return subject.getId();
    }

    @Override
    public void delect(Long id) {
        getSession().createQuery("DELETE From Subject WHERE id = " + id).executeUpdate();
    }

    @Override
    public void update(Subject subject) {
        getSession().update(subject);
    }
}
