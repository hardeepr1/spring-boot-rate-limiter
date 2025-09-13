package com.hsingh.flowcap.algorithm;

public interface RateLimiter {
    public String getName();

    boolean isAllowed(String key); // key could be user, IP, or API key
}
