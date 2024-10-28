package com.spring.controller;


import com.spring.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(roleService.findALl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

}
