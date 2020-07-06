package com.project.gelingeducation.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @Author: LL
 * @Description:
 * @Date:Create：in 2020/7/6 9:52
 */
public class UrlDeconderUtil {

    public static String decode(String s,String enc){
        try {
            return URLDecoder.decode(s,enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
