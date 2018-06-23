package com.saas.ding.compoment;

import com.alibaba.fastjson.JSONObject;
import com.saas.ding.LotteryDataConstant;
import com.saas.ding.LotterySimpleResult;
import com.saas.ding.entity.LotteryConfig;
import com.saas.utils.string.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
public class LotteryUtils {
    private static Logger logger = LoggerFactory.getLogger(LotteryUtils.class);
    private static final int  ONE_MINUTE=1000*1;
    private static Random random=new Random();
    public LotterySimpleResult getSingleData(LotteryConfig config, HttpSession session){
        if(config==null){
            return null;

        }else {
            Set<Long> firstSet=new HashSet<>();
            Set<Long> secondSet=new HashSet<>();
            LotterySimpleResult result=new LotterySimpleResult(StringUtils.uuid(),config.getName(),false);
            session.setAttribute(LotteryDataConstant.prefix+result.getId(),JSONObject.toJSONString(result));
            LotteryThread thread=new LotteryThread(result,session,false,firstSet,secondSet,0L,0L,config);
            thread.start();
            return result;
        }
    }


    @Data@NoArgsConstructor@AllArgsConstructor
    private static class LotteryThread extends Thread{
        private LotterySimpleResult result;
        private HttpSession session;
//        private String id1;
        private Boolean isFinished;
        private Set<Long> firstSet;
        private Set<Long> secondSet;
        private Long firstOver;
        private  Long secondOver;
        private LotteryConfig lotteryConfig;

        @Override
        public void run() {

            try {
                while (!isFinished){
                    if(lotteryConfig.getFrontSectionNumber()<=firstOver
                            &&lotteryConfig.getBackAreaNumber()<=secondOver){
                        break;
                    }
                    if(lotteryConfig.getFrontSectionNumber()>firstOver){
                        try {
                            Thread.sleep(random.nextInt(ONE_MINUTE));
                            Long t=System.currentTimeMillis()%lotteryConfig.getFrontSectionMax();
                            if(t!=0){
                                if(!firstSet.contains(t)){
                                    System.out.println(t);
                                    firstOver++;
                                    firstSet.add(t);
                                    result.setResult(JSONObject.toJSONString(firstSet)+","
                                    +JSONObject.toJSONString(secondSet));
                                    session.setAttribute(LotteryDataConstant.prefix+result.getId(),JSONObject.toJSONString(result));
                                }

                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else if(lotteryConfig.getBackAreaNumber()>secondOver){
                        try {
                            Thread.sleep(random.nextInt(ONE_MINUTE));
                            Long t=System.currentTimeMillis()%lotteryConfig.getBackAreaMax();
                            if(t!=0){
                                if(!secondSet.contains(t)){
                                    System.out.println(t);
                                    secondOver++;
                                    secondSet.add(t);
                                    result.setResult(JSONObject.toJSONString(firstSet)+","
                                            +JSONObject.toJSONString(secondSet));
                                    session.setAttribute(LotteryDataConstant.prefix+result.getId(),JSONObject.toJSONString(result));
                                }

                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isFinished=true;
                result.setStatus(true);
                session.setAttribute(LotteryDataConstant.prefix+result.getId(),JSONObject.toJSONString(result));
            }
        }
    }
}
