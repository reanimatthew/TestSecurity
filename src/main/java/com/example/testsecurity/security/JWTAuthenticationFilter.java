package com.example.testsecurity.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
// в данном методе мы переписываем классы Spring Secutiry под наши нужды
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/api/v1/login");
    }

    @SneakyThrows
    @Override
    // мы перекладываем логин и пароль из body jason'a в объект Authentication
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword());
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    // далее Spring берет объект Authentication, проверяет его на разные условия, если все ок - передает его в метод successfulAuthentication (ниже)

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        String login = authResult.getName();
        String token = "";
        String[] roles = authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
        try {
            Algorithm algorithm = Algorithm.HMAC256("testKey");
            token = JWT.create()
                    .withIssuer("testSecurity")
                    .withSubject(login)
                    .withArrayClaim("roles", roles)
                    .withExpiresAt(ZonedDateTime.now().plusMinutes(30).toInstant())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }

        Cookie cookie = new Cookie("JWTToken", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1800);
        response.addCookie(cookie);
    }
}
