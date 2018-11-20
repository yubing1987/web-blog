package com.ybjx.blog.checker;

import com.ybjx.blog.common.BlogException;
import com.ybjx.blog.common.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 参数校验aop
 * create by YuBing at 2018/11/20
 */
@Aspect
@Component
public class ParameterCheckAop {
    /**
     * 参数校验器
     */
    private static Validator validator;

    static {
        // 初始化参数校验器
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 参数校验处理
     * @param joinPoint 方法请求点
     * @param pc 注解
     * @return 方法请求结果
     * @throws Throwable 异常信息
     */
    @Around("@annotation(pc)")
    public Object check(ProceedingJoinPoint joinPoint, ParameterCheck pc) throws Throwable {
        Object [] args = joinPoint.getArgs();

        if(args != null && args.length > 0){
            for(Object obj: args){
                // 校验参数
                Set<ConstraintViolation<Object>> set = validator.validate(obj, pc.value());
                // 判断校验结果
                if(set != null && set.size() > 0){
                    // 拼接参数校验错误信息
                    StringJoiner joiner = new StringJoiner(", ");
                    for(ConstraintViolation constraintViolation: set){
                        joiner.add(constraintViolation.getMessage());
                    }
                    // 抛出错误信息
                    throw new BlogException(ErrorCode.PARAMETER_ERROR, "参数错误: " + joiner.toString());
                }
            }
        }
        return joinPoint.proceed();
    }
}
