package com.spring.oauth2.component;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OAuthProviderRegistry {
    private final Map<String, OAuthProvider> providers;

    public OAuthProviderRegistry(List<OAuthProvider> providerList){
        this.providers = providerList.stream()
                .collect(Collectors.toMap(OAuthProvider::getRegistrationId, Function.identity()));
    }

    public OAuthProvider getProvider(String registrationId){
        return Optional.ofNullable(providers.get(registrationId))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported Provider"));
    }
}
