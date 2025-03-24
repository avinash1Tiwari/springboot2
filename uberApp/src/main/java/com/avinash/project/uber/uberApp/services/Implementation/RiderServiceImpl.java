package com.avinash.project.uber.uberApp.services.Implementation;

import com.avinash.project.uber.uberApp.dto.DriverDto;
import com.avinash.project.uber.uberApp.dto.RideDto;
import com.avinash.project.uber.uberApp.dto.RideRequestDto;
import com.avinash.project.uber.uberApp.dto.RiderDto;
import com.avinash.project.uber.uberApp.entities.RideRequest;
import com.avinash.project.uber.uberApp.entities.Rider;
import com.avinash.project.uber.uberApp.entities.User;
import com.avinash.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.avinash.project.uber.uberApp.repositories.RideRequestRepository;
import com.avinash.project.uber.uberApp.repositories.RiderRepository;
import com.avinash.project.uber.uberApp.services.RiderService;
import com.avinash.project.uber.uberApp.strategies.RideFareCalculationStrategy;
import com.avinash.project.uber.uberApp.strategies.RideStrategyManager;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Data
@Getter

@RequiredArgsConstructor
@Service
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;                          /// here in all cases we have used "final" , to make constructor basede depandency and not used "@AutoWired"=> b/c now with final no one can change it's value i.e, full immutability
    private final RideRequestRepository rideRequestRepository;
 private final RiderRepository riderRepository;
 private final RideStrategyManager rideStrategyManager;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        System.out.println("Pickup Location: " + rideRequest.getPickupLocation());
        System.out.println("Dropoff Location: " + rideRequest.getDropOffLocation());

        RideFareCalculationStrategy strategy = rideStrategyManager.rideFareCalculationStrategy();

        if (strategy == null) {
            throw new IllegalStateException("rideFareCalculationStrategy returned null");
        }

        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        Rider currentDriver = getCurrentRider();
        rideStrategyManager.driverMatchingStrategy(currentDriver.getRating()).findMatchingDriver(rideRequest);

        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RiderDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createNewRider(User user) {

        Rider rider =  Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();

        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
//        TODO : use spring security to find the current-driver
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceAccessException(
                "Rider not found with id " + 1
        ));
    }
}
