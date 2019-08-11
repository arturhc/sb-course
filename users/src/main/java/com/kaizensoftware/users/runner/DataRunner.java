package com.kaizensoftware.users.runner;

import com.kaizensoftware.users.persistence.model.Authority;
import com.kaizensoftware.users.persistence.model.Role;
import com.kaizensoftware.users.persistence.model.RoleAuthority;
import com.kaizensoftware.users.persistence.model.User;
import com.kaizensoftware.users.persistence.repository.RoleRepository;
import com.kaizensoftware.users.persistence.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setEmail("arturh.sw@gmail.com");

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (!optionalUser.isPresent()) {

            user.setFullName("Arturo Cordero Muñiz");
            user.setPassword(passwordEncoder.encode("lenovo_1234"));

            Role role = new Role();
            role.setName("ADMIN");
            role.setDescription("Admin role");

            Optional<Role> optionalRole = roleRepository.findByName(role.getName());

            if (!optionalRole.isPresent()) {

                Authority authority = new Authority("READ_USERS");

                RoleAuthority roleAuthority = new RoleAuthority(role, authority);

                role.getRoleAuthorities().add(roleAuthority);

                roleRepository.save(role);

                user.setRole(role);

            } else {
                user.setRole(optionalRole.get());
            }

            userRepository.save(user);
        }

    }

}
