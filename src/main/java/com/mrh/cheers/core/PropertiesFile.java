package com.mrh.cheers.core;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 配置文件属性
 */
@Component
public class PropertiesFile {

    @Value("${pagehelper.helperDialect}")
    public  String helperDialect;


    @Value("${queues.test_queue}")
    public String testQueue;



}
