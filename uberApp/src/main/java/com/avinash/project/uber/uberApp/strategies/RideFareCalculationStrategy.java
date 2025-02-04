package com.avinash.project.uber.uberApp.strategies;

import com.avinash.project.uber.uberApp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDto rideRequestDto);
}
