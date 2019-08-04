package com.kaizensoftware.users.config.security.userdetails;

import com.kaizensoftware.users.persistence.model.Role;
import com.kaizensoftware.users.persistence.model.RoleAuthority;
import com.kaizensoftware.users.persistence.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.security.core.userdetails.User.withUsername;

@Component
public class CustomUserDetailsFactory {

    UserDetails createUserDetails(User user) {

        Role role = user.getRole();

        return withUsername(user.getEmail())
                .password(user.getPassword())
                .disabled(false)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .roles(getRoles(role))
                .authorities(getAuthorities(role))
                .build();

    }

    private String[] getRoles(Role role) {
        return new String[] { role.getName() };
//        return Arrays.asList(role).stream()
//                .map(r -> r.getName())
//                .toArray(String[]::new);
    }

    private String[] getAuthorities(Role role) {

        List<RoleAuthority> roleAuthorities = role.getRoleAuthorities();

        String[] authorities = new String[roleAuthorities.size()];

        for (int i = 0; i < roleAuthorities.size(); i++) {
            RoleAuthority roleAuthority = roleAuthorities.get(i);

            authorities[i] = roleAuthority.getAuthority().getName();
        }

        return authorities;
    }

}
