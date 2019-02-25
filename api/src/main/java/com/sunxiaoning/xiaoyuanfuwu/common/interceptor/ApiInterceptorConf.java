package com.sunxiaoning.xiaoyuanfuwu.common.interceptor;

import com.sunxiaoning.xiaoyuanfuwu.tools.PropertiesTools;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ApiInterceptorConf extends WebMvcConfigurationSupport {

    @Bean
    public HandlerInterceptor apiInterceptor() {
        return new ApiInterceptor();

    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 注册自己的拦截器
        // 拦截所有请求
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/api/**");

        super.addInterceptors(registry);
    }


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 接口文档
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        // 图片服务
        String _imagePath = PropertiesTools.applicationProperty("app.image.path");
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:" + _imagePath);

        // APP H5
        registry.addResourceHandler("/app/**")
                .addResourceLocations("classpath:/public/app/");

        super.addResourceHandlers(registry);
    }
}
