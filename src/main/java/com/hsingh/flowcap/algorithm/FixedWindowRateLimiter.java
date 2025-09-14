package com.hsingh.flowcap.algorithm;

import com.hsingh.flowcap.configuration.FixedWindowProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@code FixedWindowRateLimiter} implements a fixed window rate limiting algorithm.
 * <p>
 * In this algorithm, each key (such as a user ID or IP address) is allowed up to a fixed number of requests
 * in each window of time (e.g., 100 requests per minute). Once the window expires, the count resets.
 * </p>
 * <p>
 * This implementation is thread-safe and uses Java's {@link ConcurrentHashMap} and {@link AtomicInteger}
 * for concurrency control.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 *     FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(100, 60_000);
 *     boolean allowed = limiter.isAllowed("user-123");
 * </pre>
 * </p>
 *
 * @author Hardeep Singh Raike
 */
@RequiredArgsConstructor
@Component
public class FixedWindowRateLimiter implements RateLimiter{
    private final Map<String, AtomicInteger> counters = new ConcurrentHashMap<>();
    private final Map<String, Long> windowStart = new ConcurrentHashMap<>();

    private final FixedWindowProperties properties;

    @Override
    public String getName() { return "fixedwindow"; }

    @Override
    public boolean isAllowed(String key) {
        long now = System.currentTimeMillis();
        int limit = properties.getLimit();
        long windowSizeMs = properties.getWindowSizeMs();

        windowStart.putIfAbsent(key, now);
        counters.putIfAbsent(key, new AtomicInteger(0));

        if (now - windowStart.get(key) > windowSizeMs) {
            windowStart.put(key, now);
            counters.get(key).set(0);
        }

        return counters.get(key).incrementAndGet() <= limit;
    }
}
