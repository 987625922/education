package com.project.gelingeducation.common.utils;

/**
 * 文件工具类
 * @author LL
 */
public class FileUtil {

    /**
     *  获取文件的后缀名
     * @param path 文件的路径
     * @return
     */
    public static String getSuffixName(String path) {
        //获取最后一个.的位置
        int lastIndexOf = path.lastIndexOf(".");
        //获取文件的后缀名 .jpg
        String suffix = path.substring(lastIndexOf);
        return suffix;
    }
}
