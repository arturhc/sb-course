package com.kaizensoftware.users.runner;

import com.kaizensoftware.users.persistence.model.*;
import com.kaizensoftware.users.persistence.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.Setter;

@Component
public class DataRunner implements CommandLineRunner {

    @Setter(onMethod = @__(@Autowired))
    private UserRepository userRepository;

    @Setter(onMethod = @__(@Autowired))
    private RoleRepository roleRepository;

    @Setter(onMethod = @__(@Autowired))
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setEmail("arturh.sw@gmail.com");

        if (!userRepository.findByEmail(user.getEmail()).isPresent()) {


            user.setFullName("Arturo Cordero Muñiz");
            user.setPassword(passwordEncoder.encode("jaqart_"));

            Role role = new Role();
            role.setName("ADMIN");
            role.setDescription("Admin Role");

            if (!roleRepository.findByName(role.getName()).isPresent()) {

                role.getRoleAuthorities().add(
                        new RoleAuthority(role, new Authority("READ_USERS")));

                roleRepository.save(role);
            }

            user.setRole(role);

            userRepository.save(user);

        }

    }

}
