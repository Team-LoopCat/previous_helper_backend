package org.example.helper.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties (
    String prefix,
    String header,
    String secret,
    Long accessExpiration,
    Long refreshExpiration
) {}
