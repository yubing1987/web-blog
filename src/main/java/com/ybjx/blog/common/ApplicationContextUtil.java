package com.ybjx.blog.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 存放Spring Context
 * @author ybjx
 * @date 2019/5/1 23:27
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    /**
     * Spring Context
     */
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * 通过类查找相应的bean
     * @param clazz 类
     * @param <T> 类型模板
     * @return 查找到达实例
     */
    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }
}
