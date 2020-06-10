package com.project.gelingeducation.common.utils;

import com.project.gelingeducation.common.config.GLConstant;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenUtil {
    /**
     * token 加密
     *
     * @param token token
     * @return 加密后的 token
     */
    public static String encryptToken(String token) {
        try {
            DESEncryptionUtil encryptUtil =
                    new DESEncryptionUtil(GLConstant.TOKEN_CACHE_PREFIX);
            return encryptUtil.encrypt(token);
        } catch (Exception e) {
            log.info("token加密失败：", e);
            return null;
        }
    }

    /**
     * token 解密
     *
     * @param encryptToken 加密后的 token
     * @return 解密后的 token
     */
    public static String decryptToken(String encryptToken) {
        try {
            DESEncryptionUtil encryptUtil =
                    new DESEncryptionUtil(GLConstant.TOKEN_CACHE_PREFIX);
            return encryptUtil.decrypt(encryptToken);
        } catch (Exception e) {
            log.info("token解密失败：", e);
            return null;
        }
    }


}
