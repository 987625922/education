package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.utils.BeanUtils;
import com.project.gelingeducation.dao.IVideoDao;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.domain.Video;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class VideoDaoImpl implements IVideoDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List findAll() {
        return getSession().createQuery("from Video").getResultList();
    }

    @Override
    public Video findById(Long id) {
        return getSession().get(Video.class, id);
    }

    @Override
    public Long insert(Video video) {
        getSession().save(video);
        return video.getId();
    }

    @Override
    public void delect(Long id) {
        getSession().delete(getSession().get(Video.class, id));
    }

    @Override
    public void update(Video video) {
        Session session = getSession();
        Video findvideo = session.get(Video.class, video.getId());
        BeanUtils.copyPropertiesIgnoreNull(video, findvideo);
        session.update(findvideo);
    }
}
