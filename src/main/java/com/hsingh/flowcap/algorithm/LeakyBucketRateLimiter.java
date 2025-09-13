package com.hsingh.flowcap.algorithm;
import org.springframework.stereotype.Component;

@Component
public class LeakyBucketRateLimiter implements RateLimiter {

    @Override
    public String getName() { return "leakybucket"; }

    @Override
    public boolean isAllowed(String key) {
        System.out.println("Here we are in the leaky bucket");
        return false;
    }
}
