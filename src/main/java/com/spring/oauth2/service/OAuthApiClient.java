package com.spring.oauth2.service;

import org.springframework.http.ResponseEntity;

public interface OAuthApiClient {
    <T> T getUserDetails();
}
