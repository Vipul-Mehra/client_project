//package com.example.clienttracking.controller;
//
//import com.example.clienttracking.dto.keycloakDTO;
//import com.example.clienttracking.service.keycloakServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/products")
//public class keycloakController {
//
//    @Autowired
//    private keycloakServices keycloakServices;
//
//    @GetMapping("/{productId}")
//    public String getProductData(@PathVariable String productId, JwtAuthenticationToken token) {
//        String userId = token.getToken().getSubject();
//
//        keycloakDTO dto = new keycloakDTO(userId, productId);
//
//        if (!keycloakServices.userOwnsProduct(dto)) {
//            return "Access Denied";
//        }
//
//        return "Access granted to product: " + productId;
//    }
//
//    @PostMapping
//    public String addProduct(@RequestBody keycloakDTO product, JwtAuthenticationToken token) {
//        String userId = token.getToken().getSubject();
//        product.setUserId(userId);
//        keycloakServices.addProduct(product);
//        return "Product added for user: " + userId;
//    }
//}
