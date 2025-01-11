package com.EventsMaker.v1.auth;


import com.EventsMaker.v1.models.Credentials;
import com.EventsMaker.v1.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private AuthService authService;

    @Override
    protected void additionalAuthenticationChecks(
            final UserDetails d, final UsernamePasswordAuthenticationToken auth) {
    }

    @Override
    protected UserDetails retrieveUser(
            final String username,
            final UsernamePasswordAuthenticationToken authentication) {
        final String token = (String) authentication.getCredentials();
        Optional<Credentials> creds =
                authService.getCredentialsByAuthToken(token);

        return creds.map((c) ->
                        new MyUser(c.id, c.username, "")).
                orElseThrow(() -> new BadCredentialsException("user not found"));
    }
}
