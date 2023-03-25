package com.example.testsecurity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().equals("api/v1/login") ||
                request.getRequestURI().equals("/api/v1/logout");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<Cookie> jwtCookie = Optional.ofNullable(WebUtils.getCookie(request, "JWTToken"));
        if (jwtCookie.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is not found");
            return;
        }

        String token = jwtCookie.get().getValue();
        Algorithm algorithm = Algorithm.HMAC256("testKey");
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .withIssuer("testSecurity")
                .build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        String login = decodedJWT.getSubject();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(login, null, Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList())));

        filterChain.doFilter(request, response);
    }
}
