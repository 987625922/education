package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.AdminInfoDao;
import com.project.gelingeducation.domain.AdminInfo;
import com.project.gelingeducation.exception.AllException;
import com.project.gelingeducation.service.AdminInfoService;
import com.project.gelingeducation.utils.MD5Util;
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
            adminInfo.setUserName("管理员");
            adminInfo.setIsAdaim(1);
            return adminInfoDao.insert(adminInfo);
        } else {
            throw new AllException(-100, "手机号码已存在");
        }
    }

    @Override
    @Transactional
    public Object login(AdminInfo adminInfo) {
        AdminInfo info = adminInfoDao.findByPhone(adminInfo.getPhone());
        if (info != null && info.getPassword().equals(MD5Util.encrypt(adminInfo.getPhone(), adminInfo.getPassword()))) {
            return info;
        } else {
            throw new AllException(-100, "账号密码错误");
        }
    }

    @Override
    @Transactional
    public AdminInfo findById(long id) {
        return adminInfoDao.findById(id);
    }

    @Override
    @Transactional
    public void updateCoverImg(long id, String coverImg) {
        adminInfoDao.updateCoverImg(id, coverImg);
    }

    @Override
    @Transactional
    public void update(AdminInfo adminInfo) {
        adminInfoDao.update(adminInfo);
    }

    @Override
    @Transactional
    public void updateInfo(long id, String userName, String eMail, int sex, String note) {
        adminInfoDao.updateInfo(id, userName, eMail, sex, note);
    }

    @Override
    @Transactional
    public void updatePassword(long id, String oldPassword, String newPassword) {
        AdminInfo adminInfo = adminInfoDao.findById(id);
        if (adminInfo.getPassword().equals(oldPassword)) {
            adminInfoDao.updatePassword(id, MD5Util.encrypt(adminInfo.getPhone(), newPassword));
        } else {
            throw new AllException(-100, "密码错误");
        }
    }
}
