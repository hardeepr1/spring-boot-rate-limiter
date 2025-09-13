package com.hsingh.flowcap.algorithm;

import org.springframework.stereotype.Component;

@Component
public class FixedWindowRateLimiter implements RateLimiter{

    @Override
    public String getName() { return "fixedwindow"; }

    @Override
    public boolean isAllowed(String key) {
        System.out.println("Here we are in the fixed window");
        return false;
    }
}
