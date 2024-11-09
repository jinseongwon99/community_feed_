package com.jinsungwon99.common.config;

import com.jinsungwon99.auth.domain.TokenProvider;
import com.jinsungwon99.common.principal.AuthPrincipalArgumentResolver;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class AuthConfig implements WebMvcConfigurer {

    private final TokenProvider tokenProvider;

    public AuthConfig(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void addArgumentResolvers(List argumentResolvers){
        argumentResolvers.add(new AuthPrincipalArgumentResolver(tokenProvider));
    }

}
