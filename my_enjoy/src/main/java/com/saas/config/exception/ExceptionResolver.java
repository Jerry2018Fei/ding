package com.saas.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.saas.utils.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SessionException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author : 丁鹏飞
 * Date : 2018/1/15 11:21
 **/
@Component

public class ExceptionResolver extends SimpleMappingExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        logger.error(String.format("请求异常:%s", ex.getLocalizedMessage()));
        response.setCharacterEncoding("utf-8");
        response.setHeader("contentType", "text/html; charset=utf-8");
        if (isAjax(request)) {
            try {
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSONObject.toJSONString(new ExceptionResponse(false, ex.getMessage(), null)));
                writer.flush();
                logger.error(String.format("Ajax请求异常结果:%s", JSONObject.toJSONString(new ExceptionResponse(false, ex.getMessage(), null))));
            } catch (IOException e) {
                logger.error(String.format("Ajax请求处理异常:%s", e.getLocalizedMessage()));
            }
        } else {
            try {
                if (ex instanceof SessionException) {
                    request.setAttribute("msg", "登录信息已失效");
                    request.setAttribute("source", "服务器异常");
                    return new ModelAndView("/error");
                } else {
                    logger.error(ex.getClass().getCanonicalName());
                    logger.error(ex.getLocalizedMessage());
                    String msg=ex.getLocalizedMessage();
                    if(StringUtils.isEmpty(msg)){
                        msg ="未知异常";

                    }else {
                        if(msg.length()>10){
                            msg=msg.substring(0,9)+"...";
                        }
                    }
                    logger.info(String.format("/error.html?msg=%s&source=%s",changeCharset(msg),changeCharset("服务器异常")));
                    response.sendRedirect(String.format("/error.html?msg=%s&source=%s",changeCharset(msg),changeCharset("服务器异常")));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    private boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("x-requested-with");
        return null != header && "XMLHttpRequest".endsWith(header);
    }

    private String changeCharset(String msg) throws UnsupportedEncodingException {
        return  java.net.URLEncoder.encode(msg,"utf-8");
    }
}
