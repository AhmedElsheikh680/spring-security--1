package com.spring.config;


import com.spring.model.AppUser;
import com.spring.model.Role;
import com.spring.service.RoleService;
import com.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class StartupApp implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        if (roleService.findALl().isEmpty()) {
            roleService.saveAll(Arrays.asList(
                    new Role(null, "admin"),
                    new Role(null, "user"),
                    new Role(null, "employee")
            ));
        }
        if(userService.findAll().isEmpty()) {
            Set<Role> adminRole = new HashSet<>();
            adminRole.add(roleService.findByName("admin"));
            Set<Role> userRole = new HashSet<>();
            userRole.add(roleService.findByName("user"));
            Set<Role> employeeRole = new HashSet<>();
            employeeRole.add(roleService.findByName("employee"));
            userService.saveAll(
                    Arrays.asList(
                            new AppUser(null, "Ahmed Elsheikh", "ahmed", "12345", adminRole),
                            new AppUser(null, "Sayed Ali", "sayed", "12345",userRole),
                            new AppUser(null, "Kamal Elsheikh", "kamal", "12345" , employeeRole)

                    )
            );
        }
    }
}
