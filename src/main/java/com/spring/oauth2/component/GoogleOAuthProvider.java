package com.spring.oauth2.component;

import com.spring.oauth2.model.GoogleUserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleOAuthProvider implements OAuthProvider{

    @Value("${spring.oauth2.registration.name}")
    private String providerName;

    @Value("${spring.oauth2.registration.url}")
    private String providerUrl;

    @Override
    public String getRegistrationId() {
        return providerName;
    }

    @Override
    public String getUserInfoEndPoint() {
        return providerUrl;
    }

    @Override
    public Class<?> UserInfoClass() {
        return GoogleUserInfo.class;
    }
}
