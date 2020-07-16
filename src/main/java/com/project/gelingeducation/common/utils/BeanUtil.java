package com.project.gelingeducation.common.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * bean工具
 *
 * @author LL
 */
public class BeanUtil {

    /**
     * 基于spring bean的浅拷贝规则
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * spring beanUtil 特殊的浅拷贝规则
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
