package com.avinash.project.uber.uberApp.dto;

import com.avinash.project.uber.uberApp.entities.enums.PaymentMethod;
import com.avinash.project.uber.uberApp.entities.enums.RideRequestStatus;

import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDto {

    private Long id;

    private Point pickupLocation;

    private Point dropOffLocation;

    private LocalDateTime createdTime;


    private LocalDateTime rideRequest;


    private RiderDto rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideStatus;
}
