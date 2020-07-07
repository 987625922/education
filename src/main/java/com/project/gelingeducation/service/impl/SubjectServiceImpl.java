package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.common.utils.UrlDeconderUtil;
import com.project.gelingeducation.dao.ISubjectDao;
import com.project.gelingeducation.entity.Subject;
import com.project.gelingeducation.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: LL
 * @Description: 专题的Service
 */
@Service
@Transactional(readOnly = true)
public class SubjectServiceImpl implements ISubjectService {

    /**
     * 专题实体类的dao
     */
    @Autowired
    private ISubjectDao subjectDao;

    /**
     * 获取专题列表
     *
     * @param currentPage 页码
     * @param pageSize    页数
     * @return 页码为空返回全都list，不为空返回分页实体类
     */
    @Override
    public Object queryAll(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            return subjectDao.queryAll(currentPage, pageSize);
        } else {
            return subjectDao.queryAll();
        }
    }

    /**
     * 获取专题
     *
     * @param id 专题id
     * @return 专题实体类
     */
    @Override
    public Subject findById(Long id) {
        return subjectDao.findById(id);
    }

    /**
     * 添加专题
     *
     * @param subject 专题实体类
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Subject insert(Subject subject) {
        Date date = new Date();
        subject.setCreateTime(date);
        subject.setLastUpdateTime(date);
        return subjectDao.insert(subject);
    }

    /**
     * 删除专题
     *
     * @param id 专题id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delectd(Long id) {
        subjectDao.delect(id);
    }

    /**
     * 更新专题
     *
     * @param video 专题实体类
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updated(Subject video) {
        subjectDao.update(video);
    }

    /**
     * 批量删除专题
     *
     * @param ids 视频id 格式为 1,2,3
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMore(String ids) {
        subjectDao.delMore(ids);
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
    public Object searchCriteria(String name, String courseIds, Integer currentPage, Integer pageSize) {
        return subjectDao.searchCriteria(UrlDeconderUtil.decode(name,"UTF-8"), courseIds, currentPage, pageSize);
    }
}
