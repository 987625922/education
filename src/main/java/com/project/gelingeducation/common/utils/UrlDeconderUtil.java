package com.project.gelingeducation.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Author: LL
 * @Description: URL编码解密
 * @Date:Create：in 2020/7/6 9:52
 */
public class UrlDeconderUtil {

    /**
     * URL编码解密
     *
     * @param s   解密字符串
     * @param enc 字符串编码
     * @return 解密的字符串
     */
    public static String decode(String s, String enc) {
        if (s != null && s.length() > 1) {
            try {
                return URLDecoder.decode(s, enc);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
