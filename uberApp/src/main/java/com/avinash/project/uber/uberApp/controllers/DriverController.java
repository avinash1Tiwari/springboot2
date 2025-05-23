package com.avinash.project.uber.uberApp.controllers;


import com.avinash.project.uber.uberApp.dto.RideDto;
import com.avinash.project.uber.uberApp.dto.RideStartDto;
import com.avinash.project.uber.uberApp.entities.Ride;
import com.avinash.project.uber.uberApp.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> starttRide(@PathVariable Long rideRequestId, @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDto.getOtp()));
    }

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<RideDto> endRide(@PathVariable Long rideId)
    {
        return ResponseEntity.ok(driverService.endRide(rideId));
    }
}
