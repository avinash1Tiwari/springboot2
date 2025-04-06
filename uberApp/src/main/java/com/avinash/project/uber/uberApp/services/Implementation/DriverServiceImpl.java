package com.avinash.project.uber.uberApp.services.Implementation;

import com.avinash.project.uber.uberApp.dto.DriverDto;
import com.avinash.project.uber.uberApp.dto.RideDto;
import com.avinash.project.uber.uberApp.dto.RiderDto;
import com.avinash.project.uber.uberApp.entities.Drivers;
import com.avinash.project.uber.uberApp.entities.Ride;
import com.avinash.project.uber.uberApp.entities.RideRequest;
import com.avinash.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.avinash.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.avinash.project.uber.uberApp.repositories.DriverRepository;
import com.avinash.project.uber.uberApp.services.DriverService;
import com.avinash.project.uber.uberApp.services.RideRequestService;
import com.avinash.project.uber.uberApp.services.RideService;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Data
@Getter
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public RideDto acceptRide(Long rideReqId) {



        RideRequest rideRequest = rideRequestService.findRideRequestById(rideReqId);

//        checking if status is pending => then only ride can be accepted
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING))
        {
            throw new RuntimeException("RideRequest can not be accepted, status is : " + rideRequest.getRideRequestStatus());
        }
//   check if driver is available
        Drivers currentDriver = getCurrentDriver();
        if(!currentDriver.getIs_available())
        {
            throw new RuntimeException("Driver can not accept the ride due to unavailability");
        }

//        acept the ride
//        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);


        Ride ride  = rideService.createNewRide(rideRequest,currentDriver);

        return modelMapper.map(ride,RideDto.class);

    }



    @Override
    public RiderDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }


    @Override
    public Drivers getCurrentDriver() {

//        currently we are fetching default driver with Id 2
//        TODO : current driver will be fetched on implementing springSecurity
       return driverRepository.findById(2L).orElseThrow(()-> new ResourceNotFoundException("Current Driver not found"));
    }
}
