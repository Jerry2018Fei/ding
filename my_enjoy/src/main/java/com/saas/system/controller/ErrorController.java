package com.saas.system.controller;

import com.saas.utils.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 丁鹏飞
 * Date: 2017/11/29 10:42
 * Title:
 * Describer:
 */
@Controller
@RequestMapping("/")
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {
    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);
    private static final String ERROR_STATUS_CODE="javax.servlet.error.status_code";
    private static final Integer ERROR_STATUS_CODE_404=404;
    private static final String ERROR_REQUEST_URI="javax.servlet.error.request_uri";
    private static final String SERVLET_EXCEPTION="org.springframework.web.servlet.DispatcherServlet.EXCEPTION";
    private static final String SERVLET_ERROR="org.springframework.boot.autoconfigure.web.DefaultErrorAttributes.ERROR";
    private static final String ERROR_MESSAGE="javax.servlet.error.message";

    @RequestMapping("/403")
    public String unauthorizedRole() {
        return "403";
    }

    @RequestMapping("/error")
    @ResponseBody
    public void error(HttpServletRequest request) {
        String uri= (String) request.getAttribute(ERROR_REQUEST_URI);
        if(uri==null) uri="";
        if(ERROR_STATUS_CODE_404.intValue()==(Integer) request.getAttribute(ERROR_STATUS_CODE)){

            logger.error(String.format("路径:%s找不到", uri));
        }else if (!StringUtils.isEmpty(request.getAttribute(SERVLET_ERROR) )) {
            logger.error(String.format("错误信息:%s",uri+" "+request.getAttribute(SERVLET_ERROR)));
        }else if (request.getAttribute(SERVLET_EXCEPTION) instanceof MissingServletRequestParameterException) {
            logger.error(String.format("请求参数不完整:%s",request.getAttribute(ERROR_MESSAGE)));
        }else {
            logger.error(String.format("异常信息:%s", request.getAttribute(ERROR_MESSAGE)));
        }


    }
    @RequestMapping("/error.html")
    public String errorMessage(String msg, String source, Model model) {
        logger.info(String.format("source:%s,msg:%s",source,msg));
        model.addAttribute("msg",msg);
        model.addAttribute("source",source);
       return "error";


    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
