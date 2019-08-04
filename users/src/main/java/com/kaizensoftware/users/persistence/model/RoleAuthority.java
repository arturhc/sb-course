package com.kaizensoftware.users.persistence.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "role_authority")
@NoArgsConstructor
public @Data class RoleAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authority_id", referencedColumnName = "id")
    private Authority authority;

    public RoleAuthority(Role role, Authority authority) {
        this.role = role;
        this.authority = authority;
    }

}
