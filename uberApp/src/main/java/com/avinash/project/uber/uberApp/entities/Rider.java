package com.avinash.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")                      //// in Rider table, column name = user_id = primary key of User-table.
    private User user;

    private Double rating;
}
