package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.AdminInfoDao;
import com.project.gelingeducation.domain.AdminInfo;
import com.project.gelingeducation.exception.AllException;
import com.project.gelingeducation.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    private AdminInfoDao adminInfoDao;


    @Override
    @Transactional
    public Object register(AdminInfo adminInfo) {
        if (adminInfoDao.findByPhone(adminInfo.getPhone()) == null) {
            return adminInfoDao.insert(adminInfo);
        } else {
            throw new AllException(-100, "手机号码已存在");
        }
    }

    @Override
    @Transactional
    public Object login(AdminInfo adminInfo) {
        AdminInfo info = adminInfoDao.findByPhone(adminInfo.getPhone());
        if (info != null && info.getPassword().equals(adminInfo.getPassword())){
            return info;
        }else {
            throw new AllException(-100, "账号密码错误");
        }
    }
}
