package com.avinash.project.uber.uberApp.services;

//import com.avinash.project.uber.uberApp.dto.DriverDto;

import com.avinash.project.uber.uberApp.advices.ApiResponse;
import com.avinash.project.uber.uberApp.dto.DriverDto;
import com.avinash.project.uber.uberApp.dto.RideDto;
import com.avinash.project.uber.uberApp.dto.RideRequestDto;
import com.avinash.project.uber.uberApp.dto.RiderDto;
import com.avinash.project.uber.uberApp.entities.Rider;
import com.avinash.project.uber.uberApp.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;



public interface RiderService {

    ResponseEntity<ApiResponse<RideRequestDto>> requestRide(RideRequestDto rideRequestDTO);

    RiderDto cancelRide(Long rideId);


    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
