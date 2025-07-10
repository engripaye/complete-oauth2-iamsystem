package org.engripaye.resourceserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String helloUser(){
         return "HELLO USER - You are authenticated!";
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return "HELLO ADMIN - You are authenticated!";
    }
}
