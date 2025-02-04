package com.avinash.project.uber.uberApp.repositories;

import com.avinash.project.uber.uberApp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RideReository extends JpaRepository<Ride,Long> {
}
