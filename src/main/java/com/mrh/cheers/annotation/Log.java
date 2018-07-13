package com.mrh.cheers.annotation;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Log {
    // 方法注释
    String action();

    //是否打印返回值
    boolean isPrint();

}
