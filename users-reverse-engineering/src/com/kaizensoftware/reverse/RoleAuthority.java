package com.kaizensoftware.reverse;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role_authority")
public class RoleAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "authority_id", referencedColumnName = "id", nullable = false)
    private Authority authority;

}
