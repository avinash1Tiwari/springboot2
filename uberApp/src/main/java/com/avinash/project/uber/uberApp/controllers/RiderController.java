package com.avinash.project.uber.uberApp.controllers;


import com.avinash.project.uber.uberApp.advices.ApiResponse;
import com.avinash.project.uber.uberApp.dto.RideRequestDto;
import com.avinash.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public class RiderController {


    private final RiderService riderService;


    @PostMapping("/requestRide")
    public ResponseEntity<ApiResponse<RideRequestDto>> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        return riderService.requestRide(rideRequestDto);
    }
}
