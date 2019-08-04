package com.kaizensoftware.users.config.security.userdetails;

import com.kaizensoftware.users.persistence.model.User;
import com.kaizensoftware.users.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsFactory customUserDetailsFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByEmail(username);

        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(
                String.format("El email: %s no existe", username)
        ));

        return customUserDetailsFactory.createUserDetails(user);
    }
}
