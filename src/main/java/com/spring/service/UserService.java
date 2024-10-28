package com.spring.service;


import com.spring.model.AppUser;
import com.spring.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public List<AppUser> findAll() {
        return userRepo.findAll();
    }

    public AppUser findbyId(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public AppUser save(AppUser user) {
        return userRepo.save(user);
    }
    public List<AppUser> saveAll(List<AppUser> users) {
        return userRepo.saveAll(users);
    }
}
