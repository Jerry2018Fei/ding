package com.saas.ding.controller;

import com.saas.ding.compoment.LotteryConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/lottery/")
public class LotteryController {
    private static Logger logger = LoggerFactory.getLogger(LotteryController.class);

    @Resource
    private LotteryConfigUtils lotteryConfigUtils;

//    @RequestMapping("list.html")
//    public String toList() {
//        return "lottery/list";
//    }
//
//    @RequestMapping("list.json")
//    @ResponseBody
//    public List<LotteryConfig> list() throws IOException {
////        List<LotteryConfig> list = lotteryConfigUtils.query();
//
//
//        return lotteryConfigUtils.query(null);
//    }
//
//    @RequestMapping("add.html")
//    public String toAdd() {
//        return "lottery/add";
//    }
//
//    @RequestMapping(value = "add.json")
//    @ResponseBody
//    public ServerResult<String> add(@RequestBody LotteryConfig lotteryConfig) {
//        logger.info(JSONObject.toJSONString(lotteryConfig));
//        try {
//            if(lotteryConfig!=null){
//                lotteryConfig.setIsHandler(0);
//            }
//            lotteryConfigUtils.add(lotteryConfig);
//            return new ServerResult<>(true, "添加成功");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ServerResult<>(false, e.getLocalizedMessage());
//        }
//    }

}
