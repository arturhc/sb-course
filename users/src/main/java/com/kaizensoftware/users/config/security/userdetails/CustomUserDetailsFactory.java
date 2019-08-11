package com.kaizensoftware.users.config.security.userdetails;

import com.kaizensoftware.users.persistence.model.Role;
import com.kaizensoftware.users.persistence.model.RoleAuthority;
import com.kaizensoftware.users.persistence.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

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
                .authorities(getAuthorities(role))
                .build();

    }

    private String[] getAuthorities(Role role) {

        final String roleName = role.getName();

        List<RoleAuthority> roleAuthorities = role.getRoleAuthorities();

        String[] authorities = new String[roleAuthorities.size() + 1];

        for (int i = 0; i < authorities.length; i++) {

            if (i != authorities.length - 1) {

                RoleAuthority roleAuthority = roleAuthorities.get(i);

                authorities[i] = roleAuthority.getAuthority().getName();

            } else {

                Assert.isTrue(!roleName.startsWith("ROLE_"), () -> role
                        + " cannot start with ROLE_ (it is automatically added)");

                authorities[i] = "ROLE_" + roleName;
            }
        }

        return authorities;
    }

}
