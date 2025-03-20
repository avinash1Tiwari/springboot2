package com.avinash.project.uber.uberApp.dto;


import com.avinash.project.uber.uberApp.entities.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto{

    private String name;
    private String email;
    private String password;
}
