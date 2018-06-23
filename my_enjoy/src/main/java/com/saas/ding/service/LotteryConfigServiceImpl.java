package com.saas.ding.service;

import com.saas.common.BaseServiceImpl;
import com.saas.ding.dao.LotteryConfigDao;
import com.saas.ding.entity.LotteryConfig;
import com.saas.utils.string.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LotteryConfigServiceImpl extends BaseServiceImpl<LotteryConfig> implements LotteryConfigService {
    @Resource
    private LotteryConfigDao lotteryConfigDao;

    @Override
    public List<LotteryConfig> getSimpleConfigs() {
        return lotteryConfigDao.getSimpleConfigs();
    }

    @Override
    public Integer countByName(String name) {
        return lotteryConfigDao.countByName(name);
    }

    @Override
    public int insert(LotteryConfig entity) throws Exception {
        if(entity==null) throw new Exception("不能插入空对象");
        entity.setId(StringUtils.uuid());
       Integer count= countByName(entity.getName());
       if(count!=null&&count!=0){
           throw new Exception("不能新增该彩种");
       }
        return super.insert(entity);
    }
}
