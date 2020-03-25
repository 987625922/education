package com.project.gelingeducation.common.utils;

public class FileUtils {

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
