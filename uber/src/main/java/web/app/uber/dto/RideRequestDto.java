package web.app.uber.dto;

import web.app.uber.enums.PaymentMethod;
import web.app.uber.enums.RideRequestStatus;

import java.time.LocalDateTime;

public class RideRequest {
    private Long id;

    private PointDto pickupLocation;

    private PointDto dropOffLocation;

    private LocalDateTime requestedTime;

    private Rider rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;
}

