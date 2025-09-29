package com.hsingh.flowcap.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ratelimiter.leakybucket")
public class LeakyBucketProperties {
    private int bucketCapacity = 10;
    private double leakRatePerSecond = 5.0;
}
