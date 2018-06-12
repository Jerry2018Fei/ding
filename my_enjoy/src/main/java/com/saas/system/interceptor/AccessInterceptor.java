package com.saas.system.interceptor;


import com.saas.utils.date.DateUtils;
import com.saas.utils.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 丁鹏飞
 * Date: 2017/9/7 12:20
 * Title: 请求拦截器
 * Describe: 拦截所有的请求url
 */
@Component
public class AccessInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(AccessInterceptor.class);


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse,
                                Object o, Exception e) {
        Long startTime = (Long) request.getAttribute("startTime");
        if (startTime != null) {
            Long cost = System.currentTimeMillis() - startTime;
            logger.info(String.format("结束请求[http://%s:%s%s%s],结束时间：%s，消耗时间：%s毫秒",
                    request.getServerName(),
                    request.getServerPort(),
                    request.getRequestURI(),
                    StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString(),
                    DateUtils.getDate("yyyy-MM-dd HH:mm:ss.SSS"),
                    cost));
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) {
    }

    /**
     * 请求进入controller之前进行拦截，只要最终请求的方法上有accessSecurity注解，就会验证你如果
     *
     * @param request  请求
     * @param response 响应
     * @param handler  方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {

        logger.info(String.format("开始请求[http://%s:%s%s%s],请求时间：%s",
                request.getServerName(),
                request.getServerPort(),
                request.getRequestURI(),
                StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString(),
                DateUtils.getDate("yyyy-MM-dd HH:mm:ss.SSS")));
        request.setAttribute("startTime", System.currentTimeMillis());



        return true;
    }




}



