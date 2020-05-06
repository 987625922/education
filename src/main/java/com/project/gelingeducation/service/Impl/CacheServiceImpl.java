package com.project.gelingeducation.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gelingeducation.common.redis.JedisCacheClient;
import com.project.gelingeducation.domain.GLConstant;
import com.project.gelingeducation.domain.User;
import com.project.gelingeducation.service.ICacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements ICacheService {

    @Autowired
    private JedisCacheClient jedisCacheClient;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public User getUserById(long id) throws Exception {
        String userString = jedisCacheClient.get(GLConstant.USER_CACHE_PREFIX + id);
        if (StringUtils.isBlank(userString)) {
            return null;
        } else {
            return mapper.readValue(userString, User.class);
        }
    }

    @Override
    public void saveUser(User user) throws Exception {
        String userString = mapper.writeValueAsString(user);
        jedisCacheClient.set(GLConstant.USER_CACHE_PREFIX + user.getId(), userString);
    }

}
