package com.mrh.cheers.web.service;

import com.mrh.cheers.web.model.ActivityHomeBanner;

/**
 * 服务接口
 */
public interface IActivityHomeService {

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    ActivityHomeBanner selectById(Integer id);

}
