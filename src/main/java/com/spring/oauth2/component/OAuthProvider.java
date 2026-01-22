package com.spring.oauth2.component;

public interface OAuthProvider {

    String getRegistrationId();

    String getUserInfoEndPoint();

    Class<?> UserInfoClass();
}
