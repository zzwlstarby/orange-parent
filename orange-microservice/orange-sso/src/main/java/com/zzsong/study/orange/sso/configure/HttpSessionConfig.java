package com.zzsong.study.orange.sso.configure;

import com.zzsong.study.orange.common.constants.SessionConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * Created by zzsong on 2017/10/17.
 */
@EnableRedisHttpSession(redisNamespace = SessionConstants.REDIS_NAMESPACE)
public class HttpSessionConfig {

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        // 修改cookie中sessionToken的key
        cookieSerializer.setCookieName(SessionConstants.COOKIE_SESSION_KEY);
        CookieHttpSessionStrategy cookieHttpSessionStrategy = new CookieHttpSessionStrategy();
        cookieHttpSessionStrategy.setCookieSerializer(cookieSerializer);

        return cookieHttpSessionStrategy;
    }
}
