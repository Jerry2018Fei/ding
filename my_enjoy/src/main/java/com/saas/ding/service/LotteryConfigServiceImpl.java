package com.saas.ding.service;

import com.saas.common.BaseServiceImpl;
import com.saas.ding.dao.LotteryConfigDao;
import com.saas.ding.entity.LotteryConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LotteryConfigServiceImpl extends BaseServiceImpl<LotteryConfig> implements LotteryConfigService {
    @Resource
    private LotteryConfigDao lotteryConfigDao;
}
