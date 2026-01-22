package com.spring.oauth2.service;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;

@Service
public class SpringOAuthTokenServiceImpl implements OAuthTokenService{

    private final OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    public SpringOAuthTokenServiceImpl(OAuth2AuthorizedClientService clientService){
        this.authorizedClientService = clientService;
    }

    @Override
    public String getAccessToken() throws IllegalStateException{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof OAuth2AuthenticationToken auth))
            throw new IllegalStateException("User is not authenticated via OAuth2.0");

        OAuth2AuthorizedClient oAuth2AuthorizedClient =
                authorizedClientService.loadAuthorizedClient(
                        auth.getAuthorizedClientRegistrationId(),
                        auth.getName()
                );

        return oAuth2AuthorizedClient.getAccessToken().getTokenValue();
    }
}
