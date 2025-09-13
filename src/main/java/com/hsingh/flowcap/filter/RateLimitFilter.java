package com.hsingh.flowcap.filter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {
    //This will make use for RateLimiterServiceImpl
    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        filterChain.doFilter(request, response);
    }

}
