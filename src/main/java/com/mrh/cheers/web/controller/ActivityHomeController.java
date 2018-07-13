package com.mrh.cheers.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mrh.cheers.config.rabbitmq.Sender;
import com.mrh.cheers.core.PropertiesFile;
import com.mrh.cheers.exception.CustomException;
import com.mrh.cheers.web.service.IActivityHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 测试首页活动
 */

@RequestMapping("/activityHome")
@Controller
public class ActivityHomeController {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private PropertiesFile propertiesFile;

    @Resource
    private Sender sender;

    @Autowired
    private IActivityHomeService iActivityHomeService;

    private static final String DELAY = "_DELAY";

    @RequestMapping(value = "/selectById",method = RequestMethod.GET)
    @ResponseBody
    public String selectById(Integer id){
        if(null == id){
            throw new CustomException(400,"id参数不能为空！");
        }
        String content = JSONObject.toJSONString(iActivityHomeService.selectById(id));
        logger.info("根据ID查询数据完毕，准备插入延迟队列！");
        //发送延迟队列消息
        sender.send(propertiesFile.testQueue + DELAY,content);
        logger.info("根据ID查询数据，插入延迟队列，成功！");
        return content;
    }

}
