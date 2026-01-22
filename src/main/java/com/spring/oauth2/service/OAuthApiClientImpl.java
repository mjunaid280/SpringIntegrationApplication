package com.spring.oauth2.service;

import com.spring.oauth2.component.OAuthProvider;
import com.spring.oauth2.component.OAuthProviderRegistry;
import com.spring.oauth2.model.GoogleUserInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthApiClientImpl implements OAuthApiClient{

    private final OAuthTokenService oAuthTokenService;

    private final OAuthProviderRegistry oAuthProviderRegistry;
    private RestTemplate restTemplate = new RestTemplate();

    public OAuthApiClientImpl(OAuthTokenService oAuthTokenService,
                              OAuthProviderRegistry oAuthProviderRegistry){
        this.oAuthTokenService = oAuthTokenService;
        this.oAuthProviderRegistry = oAuthProviderRegistry;
    }

    public GoogleUserInfo getUserDetails(){
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        String providerId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        OAuthProvider provider = oAuthProviderRegistry.getProvider(providerId);

        String token = oAuthTokenService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity entity = new HttpEntity(headers);

        return restTemplate.exchange(
                provider.getUserInfoEndPoint(), HttpMethod.GET,entity, GoogleUserInfo.class
        ).getBody();
    }
}
