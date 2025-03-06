package web.app.uber.entity;

import jakarta.persistence.*;
import web.app.uber.enums.Role;

import java.util.Set;


@Entity
public class UberUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Role> user_roles;
}

