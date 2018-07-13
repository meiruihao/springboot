package com.mrh.cheers.web.dao;

import com.mrh.cheers.web.model.ActivityHomeBanner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ActivityHomeMapper {

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    ActivityHomeBanner selectById(@Param("id") Integer id);

}
