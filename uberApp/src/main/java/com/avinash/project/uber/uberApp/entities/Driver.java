package com.avinash.project.uber.uberApp.entities;


import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")                      //// in Rider table, column name = user_id = primary key of User-table.
    private User user;

    private Double rating;

    private Boolean is_available;

    @Column(columnDefinition = "Geometry(Point,4326")          // 4326 specifies we are talking about earth geometry
    Point currentLocation;
}
