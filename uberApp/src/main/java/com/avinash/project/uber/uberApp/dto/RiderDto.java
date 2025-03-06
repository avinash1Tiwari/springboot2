package com.avinash.project.uber.uberApp.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiderDto {

    private UserDto userDto;
    private Double rating;
}
