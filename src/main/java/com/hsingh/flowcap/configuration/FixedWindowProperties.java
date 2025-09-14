package com.hsingh.flowcap.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ratelimiter.fixedwindow")
public class FixedWindowProperties {
    private int limit = 100;
    private long windowSizeMs = 60000;
}
