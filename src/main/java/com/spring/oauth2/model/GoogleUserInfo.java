package com.spring.oauth2.model;

public record GoogleUserInfo(String sub,
                             String email,
                             String picture,
                             String name) {}
