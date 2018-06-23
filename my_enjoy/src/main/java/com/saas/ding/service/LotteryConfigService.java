package com.saas.ding.service;

import com.saas.common.IBaseService;
import com.saas.ding.entity.LotteryConfig;

import java.util.List;

public interface LotteryConfigService extends IBaseService<LotteryConfig> {

    Integer countByName(String name);

    List<LotteryConfig> getSimpleConfigs();
}
