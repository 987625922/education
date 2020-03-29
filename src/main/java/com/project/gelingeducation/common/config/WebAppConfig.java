package com.project.gelingeducation.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration//@Configuration 表示一个类声明了一个或多个@Bean方法，
//// 并且可以由Spring容器处理以在运行时为这些bean生成bean定义和服务请求。
//@EnableWebMvc//@EnableWebMvc启用默认的Spring MVC配置并注册DispatcherServlet
//// 任何用@Component和@Configuration注释的类都将被扫描。
//@ComponentScan(basePackages = {"com.project.gelingeducation"})
public class WebAppConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/a.do");
//    }

    //解决跨域问题
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }

    //图片上传
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        super.addResourceHandlers(registry);
        registry.addResourceHandler(("/image/**"))
                .addResourceLocations("file:D:\\gelingeducation\\admin\\icon");
    }

}
