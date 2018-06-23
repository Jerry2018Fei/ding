package com.saas.ding.dao;

import com.saas.common.IBaseDao;
import com.saas.ding.entity.LotteryConfig;
import com.saas.system.annotation.BppDao;

import java.util.List;

@BppDao
public interface LotteryConfigDao extends IBaseDao<LotteryConfig> {

    Integer countByName(String name);

    List<LotteryConfig> getSimpleConfigs();
}
