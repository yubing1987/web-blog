package com.ybjx.blog.common;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象相关的一些操作
 * @author ybjx
 * @date 2019/3/24 10:16
 */
public class ObjectUtils {
    /**
     * 列表拷贝
     * @param list 源数据列表
     * @param clazz 目标列表的类名称
     * @param <T> 目标类型
     * @return
     */
    public static <T> List<T> listCopy(List list, Class<T> clazz){
        List<T> result = new ArrayList<>();
        try {
            for (Object obj : list) {
                T t = clazz.newInstance();
                BeanUtils.copyProperties(obj, t);
                result.add(t);
            }
        }
        catch (Exception e){
            throw new BlogException(ErrorCode.SYSTEM_ERROR, "拷贝数据出错", e);
        }
        return result;
    }
}
