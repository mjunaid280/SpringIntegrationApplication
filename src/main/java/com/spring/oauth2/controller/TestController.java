package com.spring.oauth2.controller;

import com.spring.oauth2.service.OAuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@RestController
public class TestController {

    private final OAuthTokenService oAuthTokenService;

    public TestController(OAuthTokenService oAuthTokenService) {
        this.oAuthTokenService = oAuthTokenService;
    }

    @GetMapping("/test")
    public String get(){
        return "Hello World";
    }

    @GetMapping("/secure/token")
    public String token(){
        return oAuthTokenService.getAccessToken();
    }

//    @GetMapping("/secure/user-details")
//    public Object userDetails(@RegisteredOAuth2AuthorizedClient("google")
//                        OAuth2AuthorizedClient client){
//        String token =
//                client.getAccessToken().getTokenValue();
//        System.out.println(token);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(token);
//
//        HttpEntity<Void> entity = new HttpEntity<>(headers);
//
//        RestTemplate template = new RestTemplate();
//
//        ResponseEntity<Object> responseEntity = template.exchange(
//                "https://openidconnect.googleapis.com/v1/userinfo",
//                org.springframework.http.HttpMethod.GET,
//                entity,
//                Object.class
//        );
//
//        return responseEntity.getBody();
//    }
}
