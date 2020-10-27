package com.bitboxlab.wallet.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class OAuthConfig extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public OAuthConfig(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            com.bitboxlab.wallet.models.User creds = new ObjectMapper().readValue(request.getInputStream(), com.bitboxlab.wallet.models.User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(),new ArrayList<>()));
        }
        catch(IOException e)
        {
            throw new RuntimeException("Could not read request" + e);
        }
    }

    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
        String token = Jwts.builder()
                .setSubject(((User) authentication.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
                .signWith(SignatureAlgorithm.HS512, "SecretKeyToGenJWTs".getBytes())
                .compact();
        System.out.println(token);
        response.addHeader("Authorization","Bearer " + token);
    }

}
