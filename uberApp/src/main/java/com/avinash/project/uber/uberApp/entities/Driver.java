package com.avinash.project.uber.uberApp.entities;


import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Data
@RequiredArgsConstructor

public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @CollectionTable(name = "user_role_mapping", joinColumns = @JoinColumn(name = "user_id"))               //// in Driver table, column name = user_id = primary key of User-table.
    private User user;

    private Double rating;

    private String vehicleId;

    private Boolean is_available;

    @Column(columnDefinition = "Geometry(Point,4326)" )         // 4326 specifies we are talking about earth geometry
    Point currentLocation;    //(camelCase)
//    current_location  => cabab case
}
