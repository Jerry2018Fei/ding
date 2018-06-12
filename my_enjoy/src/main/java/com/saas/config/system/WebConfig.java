package com.saas.config.system;

import com.saas.system.interceptor.AccessInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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
//    /**
//     * 配置servlet处理
//     *
//     * @param configurer 配置
//     */
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }


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
}
