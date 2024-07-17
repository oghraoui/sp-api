package com.sbg.spapi.security;

import com.sbg.spapi.services.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private static final String CLIENT_ID = "X-CLIENT-ID";
    private static final String CLIENT_SECRET = "X-CLIENT-SECRET";
    private final ClientService clientService;

    public Authentication getAuthentication(HttpServletRequest request) {
        String clientId = request.getHeader(CLIENT_ID);
        String clientSecret = request.getHeader(CLIENT_SECRET);

        if (clientId == null || clientSecret == null || !clientService.verifyClientCredentials(clientId, clientSecret)) {
            throw new BadCredentialsException("Missing required headers");
        }

        return new ApiKeyAuthentication(clientId, clientSecret, AuthorityUtils.NO_AUTHORITIES);
    }
}
