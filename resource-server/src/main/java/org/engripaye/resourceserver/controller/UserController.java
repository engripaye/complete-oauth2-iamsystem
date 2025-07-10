package org.engripaye.resourceserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal Jwt jwt) {
        return "Welcome, " + jwt.getSubject();
    }


    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal Jwt jwt) {
        return "Welcome, " + jwt.getSubject();
    }
}
