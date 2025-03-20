package com.avinash.project.uber.uberApp.services.Implementation;

import com.avinash.project.uber.uberApp.dto.DriverDto;
import com.avinash.project.uber.uberApp.dto.SignUpDto;
import com.avinash.project.uber.uberApp.dto.UserDto;
import com.avinash.project.uber.uberApp.entities.Rider;
import com.avinash.project.uber.uberApp.entities.User;
import com.avinash.project.uber.uberApp.entities.enums.Role;
import com.avinash.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.avinash.project.uber.uberApp.repositories.UserRepository;
import com.avinash.project.uber.uberApp.services.AuthService;
import com.avinash.project.uber.uberApp.services.RiderService;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
@Data
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final RiderService riderService;
    private final ModelMapper modelMapper;

    @Override
    public String login(String username, String password) {
        return "";
    }

    @Override
    public UserDto signUp(SignUpDto signUpDto) {

        User user = userRepository.findByEmail(signUpDto.getEmail());
        if(user != null)
            throw new RuntimeConflictException("Cannot signup, User already exists with email "+signUpDto.getEmail());

        User mappedUser = modelMapper.map(signUpDto, User.class);
        mappedUser.setUser_roles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

//        create user related entities
        riderService.createNewRider(savedUser);
//        TODO add wallet related service here

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDto(Long userId) {
        return null;
    }
}
