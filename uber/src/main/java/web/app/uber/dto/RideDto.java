package web.app.uber.dto;

import web.app.uber.enums.PaymentMethod;
import web.app.uber.enums.RideRequestStatus;

import java.time.LocalDateTime;

public class Ride {
    private Long id;

    private PointDto pickupLocation;

    private PointDto dropOffLocation;

    private LocalDateTime createdTime;


    private LocalDateTime rideRequest;

    private String otp;

    private Rider rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideStatus;
}
