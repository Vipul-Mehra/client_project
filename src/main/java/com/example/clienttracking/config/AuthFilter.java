package com.example.clienttracking.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        String token = httpReq.getHeader("Authorization");

        // Product name (can be dynamic if needed)
        String product = "productA";

        // If token is missing or invalid → block
        if (token == null || !validateWithCentralizedCode(token, product)) {
            httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpRes.getWriter().write("Unauthorized: Invalid or missing token");
            return;
        }

        // If valid → continue
        chain.doFilter(request, response);
    }

    private boolean validateWithCentralizedCode(String token, String product) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<String> res = restTemplate.exchange(
                    "http://localhost:8081/auth/validate?product=" + product, // Ask central app
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            return res.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }
}
