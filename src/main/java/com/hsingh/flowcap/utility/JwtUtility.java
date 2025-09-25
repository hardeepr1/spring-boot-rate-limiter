package com.hsingh.flowcap.utility;

import com.hsingh.flowcap.configuration.TokenConfigurationProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import io.jsonwebtoken.io.Decoders;

@Component
@EnableConfigurationProperties(TokenConfigurationProperties.class)
public class JwtUtility {
    private final String issuer;
    private final TokenConfigurationProperties tokenConfigurationProperties;

    public JwtUtility(@Value("${spring.application.name}") final String issuer,
                      final TokenConfigurationProperties tokenConfigurationProperties) {
        this.issuer = issuer;
        this.tokenConfigurationProperties = tokenConfigurationProperties;
    }

    public String generateAccessToken(@NonNull final UUID userId) {
        String audience = String.valueOf(userId);

        Integer accessTokenValidity = tokenConfigurationProperties.getValidity();
        long expiration = TimeUnit.MINUTES.toMillis(accessTokenValidity);
        Date currentTimestamp = new Date(System.currentTimeMillis());
        Date expirationTimestamp = new Date(System.currentTimeMillis() + expiration);

        String encodedSecretKey = tokenConfigurationProperties.getSecretKey();
        SecretKey secretKey = getSecretKey(encodedSecretKey);

        return Jwts.builder()
                .issuer(issuer)
                .issuedAt(currentTimestamp)
                .expiration(expirationTimestamp)
                .audience().add(audience)
                .and()
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    private SecretKey getSecretKey(@NonNull final String encodedKey) {
        byte[] decodedKey = Decoders.BASE64.decode(encodedKey);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}
