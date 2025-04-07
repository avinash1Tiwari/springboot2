package com.avinash.project.uber.uberApp.dto;

import com.avinash.project.uber.uberApp.entities.enums.PaymentMethod;
import com.avinash.project.uber.uberApp.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDto {

    private Long id;

    private PointDto pickupLocation;

    private PointDto dropOffLocation;

    private LocalDateTime createdTime;


    private RideStatus rideStatus;

    private String otp;

    private RiderDto rider;
    private DriverDto driver;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    private PaymentMethod paymentMethod;

}
