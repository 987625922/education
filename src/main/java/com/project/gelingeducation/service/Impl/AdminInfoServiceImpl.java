package com.project.gelingeducation.service.Impl;

import com.project.gelingeducation.dao.AdminInfoDao;
import com.project.gelingeducation.domain.AdminInfo;
import com.project.gelingeducation.dto.PageResult;
import com.project.gelingeducation.exception.AllException;
import com.project.gelingeducation.service.AdminInfoService;
import com.project.gelingeducation.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    private AdminInfoDao adminInfoDao;

    @Override
    public Object register(AdminInfo adminInfo) {
        if (adminInfoDao.findByPhone(adminInfo.getAccount()) == null) {
            adminInfo.setUserName("管理员");
            adminInfo.setIsAdaim(1);
            adminInfo.setStatus(1);
            return adminInfoDao.insert(adminInfo);
        } else {
            throw new AllException(-100, "账号已存在");
        }
    }

    @Override
    public Object addUser(AdminInfo adminInfo) {
        if (adminInfoDao.findByPhone(adminInfo.getAccount()) == null) {
            adminInfo.setUserName("用户名");
            adminInfo.setStatus(1);
            adminInfo.setPassword(MD5Util.encrypt(adminInfo.getAccount(), adminInfo.getPassword()));
            adminInfo.setCoverImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1582740929074&di=88ebb0f61e464281d947673187acaa59&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2Fthreadcover%2Fbb%2Fa1%2F1988382.jpg");
            adminInfo.setCreateTime(String.valueOf(System.currentTimeMillis()));
            adminInfo.setModifyTime(String.valueOf(System.currentTimeMillis()));
            return adminInfoDao.insert(adminInfo);
        } else {
            throw new AllException(-100, "账号已存在");
        }
    }

    @Override
    public Object login(AdminInfo adminInfo) {
        AdminInfo info = adminInfoDao.findByPhone(adminInfo.getAccount());
        if (info != null && info.getPassword().equals(MD5Util.encrypt(adminInfo.getAccount(), adminInfo.getPassword()))) {
            return info;
        } else {
            throw new AllException(-100, "账号密码错误");
        }
    }

    @Override
    public AdminInfo findById(long id) {
//        for (int i = 0; i < 30; i++) {
//            adminInfoDao.findById(id);
//        }
        return adminInfoDao.findById(id);
    }

    @Override
    public PageResult getLists(int page, int limits) {
        page--;
        if (page < 0) {
            throw new AllException(-100, "页码少于1");
        }
        return adminInfoDao.getLists(page, limits);
    }

    @Override
    public void updateCoverImg(long id, String coverImg) {
        adminInfoDao.updateCoverImg(id, coverImg);
    }

    @Override
    public void update(AdminInfo adminInfo) {
        adminInfoDao.update(adminInfo);
    }


    @Override
    public void updatePassword(long id, String oldPassword, String newPassword) {
        AdminInfo adminInfo = adminInfoDao.findById(id);
        if (adminInfo.getPassword().equals(oldPassword)) {
//            adminInfoDao.updatePassword(id, MD5Util.encrypt(adminInfo.getAccount(), newPassword));
            adminInfo.setPassword(newPassword);
        } else {
            throw new AllException(-100, "密码错误");
        }
    }

    @Override
    public void delUser(long id) {
        adminInfoDao.delect(id);
    }

}
