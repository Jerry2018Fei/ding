package com.saas.ding.controller;

import com.alibaba.fastjson.JSONObject;
import com.saas.ding.LotterySimpleResult;
import com.saas.ding.compoment.LotteryUtils;
import com.saas.ding.entity.LotteryConfig;
import com.saas.ding.service.LotteryConfigService;
import com.saas.system.pojo.ServerResult;
import com.saas.utils.string.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/lottery/")
public class LotteryController {
    private static Logger logger = LoggerFactory.getLogger(LotteryController.class);

    @Resource
    private LotteryUtils lotteryUtils;
    @Resource
    private LotteryConfigService lotteryConfigService;

    @RequestMapping("list.html")
    public String toList() {
        return "lottery/list";
    }

    @RequestMapping("list.json")
    @ResponseBody
    public List<LotteryConfig> list() throws Exception {
        return lotteryConfigService.getAll();
    }

    @RequestMapping("add.html")
    public String toAdd() {
        return "lottery/add";
    }

    @RequestMapping(value = "add.json")
    @ResponseBody
    public ServerResult<String> add(@RequestBody LotteryConfig lotteryConfig) {
        logger.info(JSONObject.toJSONString(lotteryConfig));
        try {

            lotteryConfigService.insert(lotteryConfig);
            return new ServerResult<>(true, "添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            return new ServerResult<>(false, e.getLocalizedMessage());
        }
    }

    @RequestMapping("update.html")
    public String toUpdate(@RequestParam String id, Model model) throws Exception {
        if (StringUtils.isEmpty(id)) throw new Exception("ID 为空");
        LotteryConfig config = lotteryConfigService.getById(id);
        if (config == null) throw new Exception("配置信息为空");
        model.addAttribute("config", config);
        return "lottery/update";
    }

    @RequestMapping(value = "update.json")
    @ResponseBody
    public ServerResult<String> update(@RequestBody LotteryConfig lotteryConfig) {
        logger.info(JSONObject.toJSONString(lotteryConfig));
        try {
            lotteryConfigService.update(lotteryConfig);
            return new ServerResult<>(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ServerResult<>(false, e.getLocalizedMessage());
        }
    }

    @RequestMapping(value = "delete.json")
    @ResponseBody
    public ServerResult<String> delete(@RequestParam String id) {
        try {
            lotteryConfigService.deleteById(id);
            return new ServerResult<>(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ServerResult<>(false, e.getLocalizedMessage());
        }
    }


    @RequestMapping("data.html")
    public String toData(Model model) throws Exception {
        List<LotteryConfig> configs = lotteryConfigService.getSimpleConfigs();
        model.addAttribute("configs", configs);
        return "lottery/data";
    }

    @RequestMapping("getById.json")
    @ResponseBody
    public LotteryConfig getById(String id) {
        try {
            return lotteryConfigService.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("data.json")
    @ResponseBody
    public LotterySimpleResult data(String id, HttpServletRequest request) {
        try {
            LotteryConfig config=lotteryConfigService.getById(id);
            return lotteryUtils.getSingleData(config,request.getSession());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("dataProcess.json")
    @ResponseBody
    public LotterySimpleResult dataProcess(String key, HttpServletRequest request) {
        try {
String value=request.getSession().getAttribute(key).toString();
logger.info(value);
            LotterySimpleResult config=JSONObject.parseObject(value,LotterySimpleResult.class);
            return config;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
