package com.avinash.SequirityApp.SequirityApp.controllers;


import com.avinash.SequirityApp.SequirityApp.dto.SignUpDto;
import com.avinash.SequirityApp.SequirityApp.dto.UserDto;
import com.avinash.SequirityApp.SequirityApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody  SignUpDto signUpDto)
    {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }
}
