package com.project.gelingeducation.dao;

import com.project.gelingeducation.common.dto.PageResult;
import com.project.gelingeducation.entity.LoginLog;

import java.util.List;

/**
 * 登录日志实体类的dao接口
 *
 * @author LL
 */
public interface ILoginLogDao {

    /**
     * 添加登录日志
     *
     * @param loginLog
     */
    void insert(LoginLog loginLog);

    /**
     * 分页获取登录日志
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageResult queryAll(Integer currentPage, Integer pageSize);

    /**
     * 获取所有的登录日志
     *
     * @return
     */
    List<LoginLog> queryAll();

    /**
     * 根据用户id获取登录日志
     *
     * @param uid
     * @return
     */
    LoginLog getByUid(Long uid);

    /**
     * 根据ip获取登录日志
     *
     * @param ip
     * @return
     */
    List getLoginLogByIp(String ip);
}
