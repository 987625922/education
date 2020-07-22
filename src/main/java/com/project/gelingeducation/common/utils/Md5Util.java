package com.project.gelingeducation.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author: LL
 * @Description: MD5加密工具类
 */
public class Md5Util {

    private static final String ALGORITH_NAME = "md5";

    private static final int HASH_ITERATIONS = 5;

    /**
     * 对密码进行5次md5加密，盐为账号
     *
     * @param salt     账号
     * @param password 密码
     * @return
     */
    public static String encrypt(String salt, String password) {
        String source = StringUtils.lowerCase(salt);
        password = StringUtils.lowerCase(password);
        return new SimpleHash(ALGORITH_NAME, password,
                ByteSource.Util.bytes(source), HASH_ITERATIONS).toHex();
    }
}
