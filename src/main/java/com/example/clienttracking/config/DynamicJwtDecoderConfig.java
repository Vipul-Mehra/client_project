package com.example.clienttracking.config;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.text.ParseException;

@Configuration
public class DynamicJwtDecoderConfig {

    @Bean
    public JwtDecoder jwtDecoder() {
        return token -> {
            try {
                // Step 1: Parse JWT without verifying
                SignedJWT signedJWT = SignedJWT.parse(token);
                JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
                String issuer = claims.getIssuer(); // "iss" claim

                // Step 2: Build JWK Set URI dynamically
                String jwkSetUri = issuer + "/protocol/openid-connect/certs";

                // Step 3: Verify with correct public key
                NimbusJwtDecoder delegate = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
                return delegate.decode(token);

            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid JWT token", e);
            }
        };
    }
}
