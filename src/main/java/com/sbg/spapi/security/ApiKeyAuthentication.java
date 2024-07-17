package com.sbg.spapi.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ApiKeyAuthentication extends AbstractAuthenticationToken {

    private final String clientId;
    private final String clientSecret;

    public ApiKeyAuthentication(String clientId, String clientSecret, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return clientId;
    }
}
