package com.example.clienttracking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminProduct")
public class AdminProductController {


    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("✅ Welcome to Admin Product Service!");
    }


    @GetMapping("/data")
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("📦 Some protected product data from AdminProduct service.");
    }


    @GetMapping("/info")
    public ResponseEntity<String> info() {
        return ResponseEntity.ok("ℹ️ Admin Product Information page.");
    }
}
