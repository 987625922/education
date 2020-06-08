package com.project.gelingeducation.dao.Impl;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.common.utils.BeanUtils;
import com.project.gelingeducation.dao.IVideoDao;
import com.project.gelingeducation.domain.Video;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class VideoDaoImpl extends BaseDao implements IVideoDao {

    @Override
    public PageResult list(Integer currentPage, Integer pageSize) {
        Session session = getSession();

        String hql = "select count(*) from Video";//此处的Product是对象
        Query queryCount = session.createQuery(hql);
        long allrows = (long) queryCount.uniqueResult();

        TypedQuery<Video> query = session.createQuery("from Video");
        query.setFirstResult((currentPage - 1) * pageSize);//得到当前页
        query.setMaxResults(pageSize);//得到每页的记录数

        long totalPage = (allrows - 1) / pageSize + 1;
        List<Video> list = query.getResultList();

        PageResult pageResult = new PageResult();
        pageResult.setTotalPages(totalPage);
        pageResult.setTotalRows(allrows);
        pageResult.setLists(list);
        pageResult.setCurrentPage(currentPage);
        pageResult.setPageSize(pageSize);

        return pageResult;
    }

    @Override
    public Video findById(Long id) {
        return getSession().get(Video.class, id);
    }

    @Override
    public Video insert(Video video) {
        getSession().save(video);
        return video;
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
