package com.hsingh.flowcap.algorithm;
import com.hsingh.flowcap.configuration.FixedWindowProperties;
import com.hsingh.flowcap.configuration.LeakyBucketProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Component
public class LeakyBucketRateLimiter implements RateLimiter {
    private final Map<String, LeakyBucket> buckets = new ConcurrentHashMap<>();
    private final LeakyBucketProperties properties;

    private final int bucketCapacity = properties.getBucketCapacity();
    private final double leakRatePerSecond = properties.getLeakRatePerSecond();

    @Override
    public String getName() {
        return "leakybucket";
    }

    @Override
    public boolean isAllowed(String key) {
        LeakyBucket bucket = buckets.computeIfAbsent(key, k -> new LeakyBucket(bucketCapacity, leakRatePerSecond));
        return bucket.allowRequest();
    }
}
