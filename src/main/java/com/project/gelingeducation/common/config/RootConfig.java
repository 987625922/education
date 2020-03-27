package com.project.gelingeducation.common.config;

import com.project.gelingeducation.common.authentication.ShiroConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.SimpleDateFormat;

@Configuration
//@ComponentScan(basePackages = {"com.project.gelingeducation"},
//        excludeFilters = {
//                @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
//        })
@Import({HibernateConfig.class, ShiroConfig.class})
public class RootConfig {

    //解析Date
    @Bean
    public SimpleDateFormat dateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        return simpleDateFormat;
    }

    //上传设置
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(1024*500);
        commonsMultipartResolver.setMaxInMemorySize(4096);
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        return commonsMultipartResolver;
    }

}
