package com.avinash.project.uber.uberApp.services;

import com.avinash.project.uber.uberApp.dto.DriverDto;
import com.avinash.project.uber.uberApp.dto.RideDto;
import com.avinash.project.uber.uberApp.dto.RiderDto;

import java.util.List;

public interface DriverService {

    RiderDto acceptRide(Long rideId);

    RiderDto cancelRide(Long rideId);

    RiderDto startRide(Long rideId);

    RiderDto endRide(Long rideId);

    RiderDto rateRider(Long rideId,Integer rating);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();


}
