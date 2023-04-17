package com.example.springsecurityJB.controller;

import com.example.springsecurityJB.entity.UserInfo;
import com.example.springsecurityJB.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {
    @Autowired
    private Userservice userservice;

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from our API");
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayGoodBye() {
        return ResponseEntity.ok("Good Bye and see you later");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("Showing list");
    }

    @PostMapping("/new")
    public UserInfo addNewUser(@RequestBody UserInfo userInfo) {
        return userservice.addUser(userInfo);
    }

}
