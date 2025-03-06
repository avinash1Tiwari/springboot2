package web.app.uber.dto;

import web.app.uber.enums.Role;

import java.util.Set;

public class UberUser {
    private String name;

    private String email;

    private Set<Role> roles;
}
