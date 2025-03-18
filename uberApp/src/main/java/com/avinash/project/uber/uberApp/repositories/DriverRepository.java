package com.avinash.project.uber.uberApp.repositories;

import com.avinash.project.uber.uberApp.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//1. ST_Distance(point1,point2) => to find distance between point1 and point2.
//2. ST_DWithin(point1,d) => to find all those point which are within distance d from point point1.


@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {

    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
            "FROM drivers d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
            "ORDER BY distance " +
            "LIMIT 10", nativeQuery = true)

    List<Driver> findTenNearestDrivers(Point pickupLocation);
}
