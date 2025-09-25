package com.hsingh.flowcap.configuration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "com.hsingh.flowcap.token")
public class TokenConfigurationProperties {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9+/]*={0,2}$", message = "Secret key should be Base64 encoded.")
    private String secretKey;

    @NotNull
    @Positive
    private Integer validity;
}
