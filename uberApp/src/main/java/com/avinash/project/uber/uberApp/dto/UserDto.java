package com.avinash.project.uber.uberApp.dto;

import com.avinash.project.uber.uberApp.entities.enums.Role;
import lombok.*;

import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;

    private String email;

    private String  password;
}
