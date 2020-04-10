package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.dto.JsonData;
import com.project.gelingeducation.common.utils.FileUtils;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.CacheService;
import com.project.gelingeducation.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;


/**
 * 管理员controller
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private CacheService cacheService;

    /**
     * 获取用户信息接口
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    public Object getInfo(int id) throws Exception {
        User user = cacheService.getUserById(id);
        if (user == null) {
            user = userService.findById(id);
            cacheService.saveUser(user);
        }
        return JsonData.buildSuccess(user);
    }

    /**
     * 用户头像上传
     */
    @RequestMapping("/uploadIcon")
    public Object springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
        String path = null;
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            String userId = multiRequest.getParameter("userId");
            String time = String.valueOf(System.currentTimeMillis());
            while (iter.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    path = "D:/gelingeducation/admin/icon/" + time + userId + FileUtils.getSuffixName(file.getOriginalFilename());
                    //上传
                    file.transferTo(new File(path));
                }
            }

            userService.updateCoverImg(Long.valueOf(userId), time);
        } else {
            return JsonData.buildError("图片上传失败");
        }
        return JsonData.buildSuccess(path);
    }

    /**
     * 编辑个人信息
     *
     * @param user 个人信息
     * @return
     */
    @RequestMapping(value = "/editinfo", method = RequestMethod.POST)
    public Object update(@RequestBody User user) throws Exception {
        userService.update(user);
        cacheService.saveUser(user);
        return JsonData.buildSuccess();
    }

    /**
     * 修改密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    public Object updatePassword(long id, String oldPassword, String newPassword) {
        userService.updatePassword(id, oldPassword, newPassword);
        return JsonData.buildSuccess();
    }

    /**
     * 获取所有管理员
     *
     * @return
     */
    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    public Object lists(int currentPage, int pageSize) {
        return JsonData.buildSuccess(userService.getLists(currentPage, pageSize));
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        return JsonData.buildSuccess(userService.addUser(user));
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/deluser", method = RequestMethod.POST)
    public Object deluser(long id) {
        userService.delUser(id);
        return JsonData.buildSuccess();
    }

    /**
     * 批量删除客户
     */
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/delseluser", method = RequestMethod.POST)
    public Object delSelUser(long[] ids) {
        userService.delSelUser(ids);
        return JsonData.buildSuccess();
    }

    /**
     * 按名字搜索
     */
    @RequestMapping(value = "/selbyname", method = RequestMethod.POST)
    public Object selByName(String name, int currentPage, int pageSize) {
        return JsonData.buildSuccess(userService.selbyname(name, currentPage, pageSize));
    }

    /**
     * 添加身份
     */
    @RequestMapping(value = "/addRoles",method = RequestMethod.POST)
    public Object addRole(long userId,long[] roleIds){
        userService.addRole(userId,roleIds);
        return JsonData.buildSuccess();
    }


}
