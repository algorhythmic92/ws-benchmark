package com.algorhythmic92.ws.benchmark.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/")
    public String home() {
        return "unsecure";
    }

    @GetMapping("/secure")
    public String secure() {
        return "secure";
    }

    @GetMapping("/login/oauth2/code/google")
    public String successfulLogin() {
        return "You are authenticated!";
    }
}
