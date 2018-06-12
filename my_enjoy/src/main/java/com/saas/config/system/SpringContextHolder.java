package com.saas.config.system;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 * <p>
 * Created by zacky on 2017/6/7.
 */
@Service
@Lazy
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    /**
     * 以静态变量保存ApplicationContext,可在任意代码中取出ApplicaitonContext.
     */
    private ApplicationContext context;


    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
    public void setApplicationContext(ApplicationContext context) {
        this.context=context;
    }

    public ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public <T> T getBean(String name) {
        return (T) context.getBean(name);
    }
    public <T> T getBean(Class clazz) {
        return (T) context.getBean(clazz);
    }
    @Override
    public void destroy() throws Exception {

    }
}