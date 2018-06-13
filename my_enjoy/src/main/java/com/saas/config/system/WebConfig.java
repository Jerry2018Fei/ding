package com.saas.config.system;

import com.saas.system.interceptor.AccessInterceptor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    /**
     * @author 丁鹏飞
     * Date: 2017/12/1 10:39
     * Title: 请求拦截器
     */
    @Bean
    public AccessInterceptor accessInterceptor() {
        return new AccessInterceptor();
    }

    /**
     * @author 丁鹏飞
     * Date: 2017/12/1 10:39
     * Title: 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 添加拦截路径
        registry.addInterceptor(accessInterceptor()).addPathPatterns("/**")
        ;
    }

    /**
     * @author 丁鹏飞
     * Date: 2017/12/1 10:39
     * Title: 处理静态文件
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.ico");
    }


    //线程池
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(170);
        pool.setMaxPoolSize(190);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        pool.setQueueCapacity(1000);
        pool.setKeepAliveSeconds(60);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

//    /**
//     * 设置视图解析器
//     * @param templateEngine
//     * @return
//     */
//    @Bean
//    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine(templateEngine(templateResolver()));
//        return resolver;
//    }
//
//    /**
//     * 设置模板引擎
//     * @param templateResolver
//     * @return
//     */
//    @Bean
//    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver){
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver);
//        return engine;
//    }
//
//    /**
//     * 模板解析引擎
//     * @return
//     */
//    @Bean
//    public TemplateResolver templateResolver(){
//        TemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setPrefix("/templates/");//设置地址前缀
//        resolver.setSuffix(".html");//设置后缀
//        resolver.setCacheable(false);//设置不缓存
//        resolver.setTemplateMode("HTML5");
//        return resolver;
//
//    }
}

@Component
class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    /**
     * 以静态变量保存ApplicationContext,可在任意代码中取出ApplicaitonContext.
     */
    private ApplicationContext context;


    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
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