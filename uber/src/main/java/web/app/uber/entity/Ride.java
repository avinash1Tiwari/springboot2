package web.app.uber.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;
import web.app.uber.enums.PaymentMethod;
import web.app.uber.enums.RideRequestStatus;

import java.time.LocalDateTime;


@Entity
@Table(name = "ride_entity")
public class RideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point,4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @CreationTimestamp
    private LocalDateTime rideRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    private RiderEntity rider;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideStatus;


    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;


}
