package com.example.demo.uitl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken = request.getHeader("access");

        if(accessToken == null){
            filterChain.doFilter(request, response);
            return;
        }

        String originToken = accessToken.substring(7);

        if(!jwtTokenProvider.validateToken(originToken)){
            throw new RuntimeException("토큰 만료");
        }

        String userId = jwtTokenProvider.getUserIdFromToken(originToken);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response);

    }
}