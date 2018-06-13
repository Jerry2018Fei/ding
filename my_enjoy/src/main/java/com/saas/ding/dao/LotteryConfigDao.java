package com.saas.ding.dao;

import com.saas.common.IBaseDao;
import com.saas.ding.entity.LotteryConfig;
import com.saas.system.annotation.BppDao;

@BppDao
public interface LotteryConfigDao extends IBaseDao<LotteryConfig> {

    Integer countByName(String name);
}
