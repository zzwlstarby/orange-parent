package com.zzsong.study.orange.user.web.configure;

import com.zzsong.study.orange.user.web.feign.UserFeignClient;
import com.zzsong.study.orange.user.web.service.FileService;
import com.zzsong.study.orange.user.web.service.RedisService;
import com.zzsong.study.orange.user.web.service.UserService;
import com.zzsong.study.orange.user.web.service.impl.QiNiuFileService;
import com.zzsong.study.orange.user.web.service.impl.RedisServiceImpl;
import com.zzsong.study.orange.user.web.service.impl.UserServiceImpl;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;

/**
 * 集中管理依赖实现
 * Created by zzsong on 2017/10/25.
 */
@Configuration
public class BeanConfigure {

    @Bean
    public RedisService redisService(RedisTemplate redisTemplate) {
        return new RedisServiceImpl(redisTemplate);
    }

    @Bean
    public UserService userService(FileService fileService, UserFeignClient userFeignClient) {
        return new UserServiceImpl(fileService, userFeignClient);
    }

    @Bean
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizerustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }

    @Bean
    public FileService fileService(QiNiuFileService.QiniuConfig qiniuConfig) {
        return new QiNiuFileService(qiniuConfig);
    }
}
