package com.mrh.cheers.web.model;

import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

/**
 * 首页特定活动测试实体类
 */
@Data
public class ActivityHomeBanner implements Serializable {


    private static final long serialVersionUID = -5340701429889103044L;

    private String id = null;

    private String os = null;

    private Integer isEnable  = null;

    private Integer minVersion = null;

    private Integer maxVersion = null;

    private String title = null;

    private String img = null;

    private String function = null;

    private String condition = null;

    private String params = null;

    private Integer orderNum = null;

    private Integer startTime = null;

    private Integer endTime =  null;

    private Integer updateTime = null;

    private Integer createTime =  null;

    private Integer funId = null;

    private Integer appId =  null;

    private Integer promotionStatus = null;

    private Integer toPromotionStatus = null;

    private Integer adStatus = null;

    private Integer type = null;

    private String imgStop = null;



}
