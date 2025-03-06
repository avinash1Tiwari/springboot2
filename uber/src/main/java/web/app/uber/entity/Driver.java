package web.app.uber.entity;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;
import web.app.uber.dto.UberUser;


@Entity
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @CollectionTable(name = "user_role_mapping", joinColumns = @JoinColumn(name = "user_id"))               //// in Driver table, column name = user_id = primary key of User-table.
    private UberUserEntity user;

    private Double rating;

    private String vehicleId;

    private Boolean is_available;

    @Column(columnDefinition = "Geometry(Point,4326)" )         // 4326 specifies we are talking about earth geometry
    Point currentLocation;
}
