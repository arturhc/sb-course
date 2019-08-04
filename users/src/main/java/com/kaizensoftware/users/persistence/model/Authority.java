package com.kaizensoftware.users.persistence.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "authority")
@NoArgsConstructor
public @Data class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    public Authority(String name) {
        this.name = name;
    }

}
