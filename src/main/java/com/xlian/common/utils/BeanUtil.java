package com.xlian.common.utils;

import org.springframework.beans.BeanUtils;

public class BeanUtil {

    public static <T> T copyProperties(Object source, Class<T> target) {
        T t;
        try {
            t = target.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        BeanUtils.copyProperties(source, t);
        return t;
    }
}
