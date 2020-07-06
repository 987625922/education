package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.common.utils.UrlDeconderUtil;
import com.project.gelingeducation.dao.IVideoDao;
import com.project.gelingeducation.entity.Video;
import com.project.gelingeducation.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Author: LL
 * @Description: 视频的Service
 */
@Service
@Transactional(readOnly = true)
public class VideoServiceImpl implements IVideoService {

    /**
     * 视频实体类的dao
     */
    @Autowired
    private IVideoDao videoDao;

    /**
     * 获取视频实体类的lists
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return videoDao.queryAll(currentPage, pageSize);
        } else {
            return videoDao.queryAll();
        }
    }

    /**
     * 根据id获取视频实体类
     *
     * @param id 视频id
     * @return 视频实体类
     */
    @Override
    public Video findById(Long id) {
        return videoDao.findById(id);
    }

    /**
     * 添加视频
     *
     * @param video 视频实体
     * @return 视频实体类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Video insert(Video video) {
        Date date = new Date();
        video.setCreateTime(date);
        video.setLastUpdateTime(date);
        return videoDao.insert(video);
    }

    /**
     * 删除视频
     *
     * @param id 视频id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delectd(Long id) {
        videoDao.delect(id);
    }

    /**
     * 更新视频
     *
     * @param video 视频实体
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updated(Video video) {
        video.setLastUpdateTime(new Date());
        videoDao.update(video);
    }

    /**
     * 批量删除视频
     *
     * @param ids 视频id 格式为 1,2,3
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delMore(String ids) {
        videoDao.delMore(ids);
    }

    /**
     * 按条件搜索视频列表
     *
     * @param teacherId 教师id
     * @param name      视频名
     * @param currentPage 页码
     * @param pageSize    页数
     * @param courseIds 1,2,3格式的课程id字符串
     * @return 分页的视频list列表
     */
    @Override
    public Object searchByCriteria(String teacherId, String name, String courseIds,Integer currentPage,Integer pageSize) throws UnsupportedEncodingException {
        return videoDao.searchByCriteria(teacherId, UrlDeconderUtil.decode(name, "UTF-8"), courseIds,currentPage,pageSize);
    }
}
