package com.avinash.project.uber.uberApp.services.Implementation;

import com.avinash.project.uber.uberApp.dto.DriverDto;
import com.avinash.project.uber.uberApp.dto.PointDto;
import com.avinash.project.uber.uberApp.dto.RideDto;
import com.avinash.project.uber.uberApp.dto.RiderDto;
import com.avinash.project.uber.uberApp.entities.Drivers;
import com.avinash.project.uber.uberApp.entities.Ride;
import com.avinash.project.uber.uberApp.entities.RideRequest;
import com.avinash.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.avinash.project.uber.uberApp.entities.enums.RideStatus;
import com.avinash.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.avinash.project.uber.uberApp.repositories.DriverRepository;
import com.avinash.project.uber.uberApp.services.DriverService;
import com.avinash.project.uber.uberApp.services.RideRequestService;
import com.avinash.project.uber.uberApp.services.RideService;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeException("RideRequest cannot be accepted, status is "+ rideRequest.getRideRequestStatus());
        }

        Drivers currentDriver = getCurrentDriver();
        if(!currentDriver.getIs_available()) {
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }

        currentDriver.setIs_available(false);
        Drivers savedDriver = driverRepository.save(currentDriver);

        Ride ride = rideService.createNewRide(rideRequest, savedDriver);

//        return modelMapper.map(ride, RideDto.class);


//        -----------------------------------
        RideDto rideDto = new RideDto();
        rideDto.setId(ride.getId());

// Map Point to PointDto for pickup and drop-off
        Point pickup = ride.getPickupLocation();
        PointDto pickupDto = new PointDto("Point", new double[]{pickup.getX(), pickup.getY()});
        rideDto.setPickupLocation(pickupDto);

// Create PointDto for drop-off location
        Point dropOff = ride.getDropOffLocation();
        PointDto dropOffDto = new PointDto("Point", new double[]{dropOff.getX(), dropOff.getY()});
        rideDto.setDropOffLocation(dropOffDto);

        rideDto.setPickupLocation(pickupDto);
        rideDto.setDropOffLocation(dropOffDto);

// Set simple fields
        rideDto.setDriver(modelMapper.map(ride.getDriver(), DriverDto.class));
        rideDto.setRider(modelMapper.map(ride.getRider(), RiderDto.class));
        rideDto.setCreatedTime(ride.getCreatedTime());
        rideDto.setPaymentMethod(ride.getPaymentMethod());
        rideDto.setRideStatus(ride.getRideStatus());
        rideDto.setOtp(ride.getOtp());
        rideDto.setFare(ride.getFare());
        rideDto.setStartedAt(ride.getStartedAt());
        rideDto.setEndedAt(ride.getEndedAt());
//        -----------------------------------
return rideDto;
    }



    @Override
    public RiderDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    @Transactional
    public RideDto startRide(Long rideId, String otp) {

//        1.get ride
        Ride ride = rideService.getRideById(rideId);
        Drivers driver = getCurrentDriver();

//        2 to check if same driver is starting
        if(!driver.equals(ride.getDriver()))
        {
            throw new RuntimeException("Driver can not start the ride as he has not accepted it earlier");
        }

//        3.start only if ride is confirmed
        System.out.println("=============================================statuss 000000000000000000000000000   :  " +RideStatus.ONGOING + "==========================================================" );

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED))
        {
            throw new RuntimeException("Ride status is not confirmed , hence can not be started , status : " + ride.getRideStatus());
        }

//        4. check otp
        if(!ride.getOtp().equals(otp))
        {
            throw new RuntimeException("OTP is not valid , otp : " + ride.getOtp());
        }
//   5.     start the ride
    ride.setStartedAt(LocalDateTime.now());
        System.out.println("=============================================statuss    :  " +RideStatus.ONGOING + "==========================================================" );
        Ride savedRide = rideService.updateRideStatus(ride,RideStatus.ONGOING);

        Point pickup = savedRide.getPickupLocation();
        Point dropOff = savedRide.getDropOffLocation();
        savedRide.setPickupLocation(null);
        savedRide.setDropOffLocation(null);


        RideDto rideDto =  modelMapper.map(savedRide, RideDto.class);
        PointDto pickupDto = new PointDto("Point", new double[]{pickup.getX(), pickup.getY()});
        rideDto.setPickupLocation(pickupDto);
        PointDto dropOffDto = new PointDto("Point", new double[]{dropOff.getX(), dropOff.getY()});
        rideDto.setDropOffLocation(dropOffDto);
         return rideDto;
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
       return driverRepository.findById(5L).orElseThrow(()-> new ResourceNotFoundException("Current Driver not found"));
    }
}
