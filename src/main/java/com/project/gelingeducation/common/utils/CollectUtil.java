package com.project.gelingeducation.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: LL
 * @Description:
 * @Date:Create：in 2020/7/8 17:09
 */
public class CollectUtil {

    /**
     * set转list
     *
     * @param set
     * @return
     */
    public static List setToList(Set set) {
        List list = new ArrayList();
        set.forEach(o -> {
            list.add(o);
        });
        return list;
    }
}
