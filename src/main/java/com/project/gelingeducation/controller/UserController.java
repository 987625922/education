package com.project.gelingeducation.controller;

import com.project.gelingeducation.common.annotation.Log;
import com.project.gelingeducation.common.dto.JsonResult;
import com.project.gelingeducation.common.exception.StatusEnum;
import com.project.gelingeducation.common.vo.UserPassVo;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;

/**
 * @author LL
 * @Description: 管理员controller
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    /**
     * user实体类的service
     */
    @Autowired
    private IUserService userService;

    /**
     * 获取所有的用户
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Log("获取所有用户")
    @RequestMapping(value = "/lists")
    public Object queryAll(@RequestParam(required = false) Integer currentPage,
                           @RequestParam(required = false) Integer pageSize) {
        return JsonResult.buildSuccess(userService.queryAll(currentPage, pageSize));
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Log("获取用户信息")
    @RequestMapping(value = "/get_info")
    public Object getInfo() {
        User shiroUser = (User) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findById(shiroUser.getId());
        return JsonResult.buildSuccess(user);
    }

    /**
     * 上传用户头像
     *
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @Log("上传用户头像")
    @RequestMapping("/upload_icon")
    public Object springUpload(HttpServletRequest request)
            throws IllegalStateException, IOException {
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
                    path = System.getProperty("user.home") + "/.gelingeducation/file/tmp";
//                    path = "D:/gelingeducation/admin/icon/" + time + userId + FileUtil.getSuffixName(file.getOriginalFilename());
                    //上传
                    file.transferTo(new File(path));
                }
            }
            userService.updateCoverImg(Long.valueOf(userId), time);
        } else {
            return JsonResult.buildStatus(StatusEnum.UPFILE_IMGAGE_FAILE);
        }
        return JsonResult.buildSuccess(path);
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @Log("更新用户")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(@RequestBody User user) {
        User shiroUser = (User) SecurityUtils.getSubject().getPrincipal();
        user.setId(shiroUser.getId());
        userService.update(user);
        return JsonResult.buildSuccess();
    }

    /**
     * 修改密码
     *
     * @param userPassVo
     * @return
     */
    @Log("修改密码")
    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    public Object updatePassword(@RequestBody UserPassVo userPassVo) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        userService.updatePassword(user.getId(), userPassVo.getOldPass(),
                userPassVo.getNewPass());
        return JsonResult.buildSuccess();
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Log("添加用户")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addUser(@RequestBody User user) {
        return JsonResult.buildSuccess(userService.addUser(user));
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Log("删除用户")
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/delete")
    public Object delete(Long id) {
        userService.delUser(id);
        return JsonResult.buildSuccess();
    }

    /**
     * 批量删除用户
     *
     * @param ids
     * @return
     */
    @Log("批量删除用户")
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/batches_delete")
    public Object delMoreUser(String ids) {
        userService.delSelUser(ids);
        return JsonResult.buildSuccess();
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param name        用户名
     * @param currentPage
     * @param pageSize
     * @return
     * @throws UnsupportedEncodingException
     */
    @Log("按用户名搜索用户")
    @RequestMapping(value = "/find_by_name")
    public Object findByName(String name, Integer currentPage, Integer pageSize)
            throws UnsupportedEncodingException {
        return JsonResult.buildSuccess(userService.selbyname(URLDecoder.decode(name, "UTF-8"),
                currentPage, pageSize));
    }

    /**
     * 通过用户id给用户添加身份
     *
     * @param userId 用户id
     * @param roleId 身份id
     * @return
     */
    @Log("通过用户id给用户添加身份")
    @RequiresPermissions("user:root")
    @RequestMapping(value = "/add_roles_binding_user_id")
    public Object addRole(Long userId, Long roleId) {
        userService.addRole(userId, roleId);
        return JsonResult.buildSuccess();
    }
}
