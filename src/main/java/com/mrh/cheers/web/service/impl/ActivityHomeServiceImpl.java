package com.mrh.cheers.web.service.impl;

import com.mrh.cheers.annotation.Log;
import com.mrh.cheers.web.dao.ActivityHomeMapper;
import com.mrh.cheers.web.model.ActivityHomeBanner;
import com.mrh.cheers.web.service.IActivityHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ActivityHomeServiceImpl implements IActivityHomeService {

    private static final Logger logger = LoggerFactory.getLogger(ActivityHomeServiceImpl.class);

    @Autowired
    private ActivityHomeMapper activityHomeMapper;

    @Log(action = "【根据ID查询首页特定活动】",isPrint = true)
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,readOnly = true)
    @Override
    public ActivityHomeBanner selectById(Integer id) {
        ActivityHomeBanner activityHomeBanner = null;
        try {
            activityHomeBanner = activityHomeMapper.selectById(id);
        }catch (Exception e){
            logger.error("查询首页特定活动数据，异常:"+e.getMessage());
            throw new RuntimeException();
        }
        return activityHomeBanner;
    }
}
