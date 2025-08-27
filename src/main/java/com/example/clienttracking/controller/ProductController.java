package com.example.clienttracking.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final RestTemplate restTemplate = new RestTemplate();

    // Only centralized base URL is fixed (can be changed in properties)
    @Value("${centralized.service.url}")
    private String centralizedServiceUrl;

    @GetMapping("/data")
    public ResponseEntity<?> getProducts(
            @RequestHeader("Authorization") String token,
            @RequestParam("realm") String realm,
            @RequestParam("product") String product // frontend must send this
    ) {
        String url = centralizedServiceUrl + "/auth/validate/" + realm + "/" + product;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("✅ Valid user for realm: " + realm + ", product: " + product);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Invalid user for realm: " + realm);
        }
    }

}
