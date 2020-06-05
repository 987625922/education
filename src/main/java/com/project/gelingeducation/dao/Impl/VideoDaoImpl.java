package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.dao.IVideoDao;
import com.project.gelingeducation.domain.Video;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class VideoDaoImpl implements IVideoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<Video> findAll() {
        TypedQuery<Video> query = getSession().createQuery("from Video");
        return query.getResultList();
    }

    @Override
    public Video findById(Long id) {
        Video video = getSession().get(Video.class, id);
        return video;
    }

    @Override
    public long insert(Video video) {
        getSession().save(video);
        return video.getId();
    }

    @Override
    public void delect(Long id) {
        getSession().delete(getSession().get(Video.class, id));
    }

    @Override
    public void update(Video video) {
        getSession().update(video);
    }
}
