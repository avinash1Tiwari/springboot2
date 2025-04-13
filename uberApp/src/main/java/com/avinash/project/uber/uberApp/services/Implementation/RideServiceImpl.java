package com.avinash.project.uber.uberApp.services.Implementation;


import com.avinash.project.uber.uberApp.entities.Drivers;
import com.avinash.project.uber.uberApp.entities.Ride;
import com.avinash.project.uber.uberApp.entities.RideRequest;
import com.avinash.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.avinash.project.uber.uberApp.entities.enums.RideStatus;
import com.avinash.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.avinash.project.uber.uberApp.repositories.RideRepository;
import com.avinash.project.uber.uberApp.services.RideRequestService;
import com.avinash.project.uber.uberApp.services.RideService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Data
@RequiredArgsConstructor
@Service
public class RideServiceImpl implements RideService {

    private final RideRequestService rideRequestService;

    private final ModelMapper modelMapper;

    private final RideRepository rideRepository;



    @Override
    public Ride getRideById(Long rideId) {
       return rideRepository.findById(rideId).orElseThrow(()-> new ResourceNotFoundException("ride with given rideId not present"));
    }

    @Override
    public void mathWithDriver(RideRequest rideRequest) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Drivers driver) {

        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride = modelMapper.map(rideRequest,Ride.class);

        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOTP());
        ride.setId(null);

        rideRequestService.update(rideRequest);

        return rideRepository.save(ride);

    }



    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);

    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest) {
        return null;
    }

    private String generateRandomOTP() {
        Random random = new Random();

        int otpInt = random.nextInt(10000);          /// generate a random number in range(0,10000);

        return String.format("%04d",otpInt);             //// it converts otpInt generate to always in four chars   Ex => otpInt = 12 =>   String.format("%04d",otpInt);  => "0014"
    }
}
