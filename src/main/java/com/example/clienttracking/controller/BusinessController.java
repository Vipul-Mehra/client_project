package com.example.clienttracking.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BusinessController {

    @GetMapping("/test")
    public String test() {
        return "✅ Token Verified: You are authenticated";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userData() {
        return "👤 This is USER data";
    }

    @GetMapping("/data")
    @PreAuthorize("hasRole('admin')")
    public String adminData() {
        return "🔐 This is ADMIN data";
    }

}
