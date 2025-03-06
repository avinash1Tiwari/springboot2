package web.app.uber.entity;

import jakarta.persistence.*;


@Entity
public class RiderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")                      //// in Rider table, column name = user_id = primary key of User-table.
    private UberUserEntity user;

    private Double rating;
}
