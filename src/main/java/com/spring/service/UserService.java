package com.spring.service;


import com.spring.model.AppUser;
import com.spring.repo.UserRepo;
import com.spring.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public List<AppUser> findAll() {
        return userRepo.findAll();
    }

    public AppUser findbyId(Long id) {
        return userRepo.findById(id).orElse(null);
    }



    public AppUser save(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public List<AppUser> saveAll(List<AppUser> users) {
            users.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
        return userRepo.saveAll(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = userRepo.findByUserName(username);
        if (!appUser.isPresent()) {
            throw  new UsernameNotFoundException("Sorry, username: " + username + " Not Found");
        }

        return new AppUserDetails(appUser.get());
    }
}
