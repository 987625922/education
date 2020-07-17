package com.project.gelingeducation.service.impl;

import com.project.gelingeducation.common.config.GLConstant;
import com.project.gelingeducation.common.dto.WebDataDto;
import com.project.gelingeducation.common.exception.AllException;
import com.project.gelingeducation.common.exception.StatusEnum;
import com.project.gelingeducation.common.utils.*;
import com.project.gelingeducation.entity.LoginLog;
import com.project.gelingeducation.entity.User;
import com.project.gelingeducation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: LL
 * @Description: 视频的Service
 */
@Transactional(readOnly = true)
@Service
public class WebDataBeanServiceImpl implements IWebDataBeanService {

    /**
     * 用户实体类service
     */
    @Autowired
    private IUserService userService;
    /**
     * 登录日志实体类service
     */
    @Autowired
    private ILoginLogService loginLogService;
    /**
     * 专题实体类的service
     */
    @Autowired
    private ISubjectService subjectService;
    /**
     * 课程实体类的service
     */
    @Autowired
    private ICourseService courseService;
    /**
     * 视频实体类的service
     */
    @Autowired
    private IVideoService videoService;
    /**
     * rides工具类
     */
    @Autowired
    RedisTemplateUtil templateUtil;

    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    @Override
    public Object login(String account, String password,HttpServletRequest request) {
        //通过用户名获取用户
        User reUser = userService.findUserByAccount(account);
        if (reUser == null) {
            throw new AllException(StatusEnum.NO_USER);
        } else if (!reUser.getPassword().equals(Md5Util.encrypt(account.toLowerCase(), password))) {
            throw new AllException(StatusEnum.ACCOUNT_PASSWORD_ERROR);
        } else if (reUser.getStatus() == 0) {
            throw new AllException(StatusEnum.BAN_USER);
        }
        //添加登录数量
        addLoginMun(reUser);
        //更新登录log,可以开启一个格外的线程去处理
        ExecutorsUtils.getInstance().execute(() -> loginLogService.saveOrUpdateLoginLogByUid(reUser,request));
        //返回uid和jwtToken
        String token = JwtUtil.sign(reUser.getAccount(), reUser.getPassword());
        HashMap userMap = new HashMap(2);
        userMap.put("id", reUser.getId());
        userMap.put("token", token);
        //设置redis token缓存和过期时间
        templateUtil.set(GLConstant.TOKEN_CACHE_PREFIX + TokenUtil.encryptToken(token)
                + "." + reUser.getAccount(), token, GLConstant.TOKEN_CACHE_TIME_SECONDS);
        return userMap;
    }


    /**
     * 添加登录数
     */
    @Override
    public void addLoginMun(User user) {
        //获取所有登录次数
        String allLoginNumStr = (String) templateUtil.get(GLConstant.ALL_LOGIN_NUM_KEY);
        //判断所有登录数是否为空，为空就赋值
        if (allLoginNumStr == null) {
            allLoginNumStr = "0";
        }
        Integer allLoginNum = Integer.valueOf(allLoginNumStr);
        allLoginNum++;

        //获取当天登录次数
        String todayLoginNumStr = (String) templateUtil.get(GLConstant.TODAY_LOGIN_NUM_KEY);
        //判断当天登录数是否为空，为空就赋值
        if (todayLoginNumStr == null) {
            todayLoginNumStr = "0";
        }
        Integer todayLoginNum = Integer.valueOf(todayLoginNumStr);
        todayLoginNum++;

        //获取request
        HttpServletRequest servletRequest = SpringContextUtil.getHttpServletRequest();
        if (servletRequest != null) {
            //获取request的ip
            String ip = HttpUtil.getIp(servletRequest);
            if (!StringUtils.isEmpty(ip)) {
                //根据ip获取登录信息列表
                List<LoginLog> loginLogList = loginLogService.getLoginLogByIp(ip);
                //如果登录信息列表为空说明此ip没有登录过，ip数量加一
                if (loginLogList == null) {
                    addTodayIpNum();
                }
                //登录列表不为空
                else {
                    //此ip今天是否登录过
                    AtomicBoolean isTodayLogin = new AtomicBoolean(false);
                    loginLogList.forEach(loginLog -> {
                        if (DateUtil.dateIsToday(loginLog.getLoginTime())) {
                            isTodayLogin.set(true);
                        }
                    });
                    //如果次ip今天没有登录，ip数量加一
                    if (!isTodayLogin.get()) {
                        addTodayIpNum();
                    }
                }
            }
        }
        //保存当天登录次数
        templateUtil.set(GLConstant.TODAY_LOGIN_NUM_KEY, String.valueOf(todayLoginNum));
        //保存所有登录次数
        templateUtil.set(GLConstant.ALL_LOGIN_NUM_KEY, String.valueOf(allLoginNum));
    }

    /**
     * 清空今天的登录数
     */
    @Override
    public void clearTodayLoginMun() {
        //保存当天登录次数
        templateUtil.set(GLConstant.TODAY_LOGIN_NUM_KEY, "0");
        //保存当天登录ip次数
        templateUtil.set(GLConstant.TODAY_LOGIN_IP_NUM_KEY, "0");

    }

    /**
     * 获取前端首页的信息
     *
     * @return
     */
    @Override
    public WebDataDto getWebData() {
        WebDataDto dataDto = new WebDataDto();
        //设置登录总数
        dataDto.setAllLoginNum(Integer.valueOf((String) templateUtil.get(GLConstant.ALL_LOGIN_NUM_KEY)));
        //设置今天登录ip数
        String todayLoginIpNum = (String) templateUtil.get(GLConstant.TODAY_LOGIN_IP_NUM_KEY);
        if (todayLoginIpNum == null) {
            todayLoginIpNum = "0";
        }
        dataDto.setTodayLoginIpNum(Integer.valueOf(todayLoginIpNum));
        //设置今天登录数
        dataDto.setTodayLoginNum(Integer.valueOf((String) templateUtil.get(GLConstant.TODAY_LOGIN_NUM_KEY)));
        //设置专题数量
        dataDto.setSubjectNum(subjectService.getTotalNumber());
        //设置课程数量
        dataDto.setCourseNum(courseService.getTotalNumber());
        //设置视频数量
        dataDto.setVideosNum(videoService.getTotalNumber());
        return dataDto;
    }

    /**
     * 添加今天登录的ip数量
     */
    @Override
    public void addTodayIpNum() {
        //获取ip登录次数
        String todayLoginIpNumKeyStr = (String) templateUtil.get(GLConstant.TODAY_LOGIN_IP_NUM_KEY);
        //判断ip登录数是否为空，为空就赋值
        if (todayLoginIpNumKeyStr == null) {
            todayLoginIpNumKeyStr = "0";
        }
        Integer todayLoginIpNumKey = Integer.valueOf(todayLoginIpNumKeyStr);
        //ip数量加一
        todayLoginIpNumKey++;
        //ip数量存入缓存
        templateUtil.set(GLConstant.TODAY_LOGIN_IP_NUM_KEY, String.valueOf(todayLoginIpNumKey));
    }
}

