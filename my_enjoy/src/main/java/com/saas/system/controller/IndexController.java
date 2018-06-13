package com.saas.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView model=new ModelAndView();
        model.addObject("i","123123");
        model.setViewName("index");
        return model;
    }


}
