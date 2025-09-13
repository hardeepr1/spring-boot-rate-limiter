package com.hsingh.flowcap.factory;

import com.hsingh.flowcap.algorithm.RateLimiter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RateLimiterFactory {

    private final Map<String, RateLimiter> algorithms;

    public RateLimiterFactory(List<RateLimiter> implementations) {
        this.algorithms = implementations.stream()
                .collect(Collectors.toMap(RateLimiter::getName, Function.identity()));
    }

    public RateLimiter getRateLimiter(String name) {
        return algorithms.getOrDefault(name, algorithms.get("fixedwindow")); // default
    }
}
