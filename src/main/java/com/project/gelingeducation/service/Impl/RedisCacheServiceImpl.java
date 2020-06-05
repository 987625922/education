package com.project.gelingeducation.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gelingeducation.common.config.GLConstant;
import com.project.gelingeducation.common.redis.JedisCacheClient;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.IRedisCacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * redis缓存服务类
 */
@Service
public class RedisCacheServiceImpl implements IRedisCacheService {

    @Autowired
    private JedisCacheClient jedisCacheClient;

    @Autowired
    private ObjectMapper mapper;

    /**
     * 通过id从redis中获取用户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public User getUserById(Long id) throws Exception {
        String userString = jedisCacheClient.get(GLConstant.USER_CACHE_PREFIX + id);
        if (StringUtils.isBlank(userString)) {
            return null;
        } else {
            return mapper.readValue(userString, User.class);
        }
    }

    /**
     * 保存用户信息
     *
     * @param user
     * @throws Exception
     */
    @Override
    public void saveUser(User user) throws Exception {
        String userString = mapper.writeValueAsString(user);
        jedisCacheClient.set(GLConstant.USER_CACHE_PREFIX + user.getId(), userString);
    }

}
