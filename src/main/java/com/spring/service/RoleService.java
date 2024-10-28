package com.spring.service;


import com.spring.model.Role;
import com.spring.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepo roleRepo;

    public List<Role> findALl() {
        return roleRepo.findAll();
    }

    public Role findById(Long id) {
        return roleRepo.findById(id).orElse(null);
    }

    public Role findByName(String name) {
        return roleRepo.findByName(name);
    }

    public Role save(Role role) {
        return roleRepo.save(role);
    }

    public List<Role> saveAll(List<Role> roles) {
        return roleRepo.saveAll(roles);
    }
}
