package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 */
@Target(ElementType.METHOD) //表明这个注解只能加在方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    OperationType value();
}
