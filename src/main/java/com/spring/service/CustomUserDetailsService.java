package com.spring.service;

import com.spring.model.AppUser;
import com.spring.repo.UserRepo;
import com.spring.security.AppUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = userRepo.findByUserName(username);
        if (!appUser.isPresent()) {
            throw  new UsernameNotFoundException("Sorry, username: " + username + " Not Found");
        }
//        return new User(appUser.get().getUserName(),
//                appUser.get().getPassword(),
//                getAuthorities(appUser.get()));
        return new AppUserDetails(appUser.get());
    }

//    public static List<GrantedAuthority> getAuthorities(AppUser user) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (!user.getRoles().isEmpty())  {
//            user.getRoles().forEach(role ->  {
//                authorities.add(new SimpleGrantedAuthority(role.getName()));
//            });
//        }
//        return authorities;
//    }
}
