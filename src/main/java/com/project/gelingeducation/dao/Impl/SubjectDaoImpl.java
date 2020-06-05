package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.ISubjectDao;
import com.project.gelingeducation.domain.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class SubjectDaoImpl implements ISubjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<Subject> findAll() {
        TypedQuery<Subject> query = getSession().createQuery("from Subject");
        return query.getResultList();
    }

    @Override
    public Subject findById(Long id) {
        Subject subject = getSession().get(Subject.class, id);
        return subject;
    }

    @Override
    public long insert(Subject subject) {
        getSession().save(subject);
        return subject.getId();
    }

    @Override
    public void delect(Long id) {
        getSession().delete(getSession().get(Subject.class, id));
    }

    @Override
    public void update(Subject subject) {
        getSession().update(subject);
    }
}
