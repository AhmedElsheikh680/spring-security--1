package com.spring.controller;


import com.spring.service.UserService;
import jakarta.servlet.http.PushBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(value = {"http://localhost:4200", "https://google.com"})
public class UserController {
    private final UserService userService;

    @GetMapping("")
//    @CrossOrigin(value = {"http://localhost:4200", "https://google.com"})
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
//    @CrossOrigin(value = {"http:localhost:4200"})
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findbyId(id));
    }
}
