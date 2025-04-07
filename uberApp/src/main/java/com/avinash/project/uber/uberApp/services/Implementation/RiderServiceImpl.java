package com.avinash.project.uber.uberApp.services.Implementation;

import com.avinash.project.uber.uberApp.advices.ApiResponse;
import com.avinash.project.uber.uberApp.dto.DriverDto;
import com.avinash.project.uber.uberApp.dto.RideDto;
import com.avinash.project.uber.uberApp.dto.RideRequestDto;
import com.avinash.project.uber.uberApp.dto.RiderDto;
import com.avinash.project.uber.uberApp.entities.Drivers;
import com.avinash.project.uber.uberApp.entities.RideRequest;
import com.avinash.project.uber.uberApp.entities.Rider;
import com.avinash.project.uber.uberApp.entities.User;
import com.avinash.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.avinash.project.uber.uberApp.repositories.RideRequestRepository;
import com.avinash.project.uber.uberApp.repositories.RiderRepository;
import com.avinash.project.uber.uberApp.services.RiderService;
import com.avinash.project.uber.uberApp.strategies.RideStrategyManager;
import com.avinash.project.uber.uberApp.utils.GeometryUtil;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTReader;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
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
    @Transactional
    public ResponseEntity<ApiResponse<RideRequestDto>> requestRide(RideRequestDto rideRequestDTO) {
        try {
            String pickupWKT = GeometryUtil.convertPointDtoToWKT(rideRequestDTO.getPickupLocation());
            String dropOffWKT = GeometryUtil.convertPointDtoToWKT(rideRequestDTO.getDropOffLocation());

            System.out.println("Pickup Location WKT: " + pickupWKT);
            System.out.println("Dropoff Location WKT: " + dropOffWKT);

            // Get Current Rider
            Rider rider = getCurrentRider();

            // Calculate Fare
            RideRequest rideRequest1 = modelMapper.map(rideRequestDTO, RideRequest.class);
            rideRequest1.setRideRequestStatus(RideRequestStatus.PENDING);
            rideRequest1.setRider(rider);

            Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest1);

            // Call Native Query to Save RideRequest
            rideRequestRepository.saveRideRequest(
                    dropOffWKT,                          // DropOff Location in WKT
                    fare,                                // Fare calculated
                    rideRequestDTO.getPaymentMethod().toString(),  // Payment method
                    pickupWKT,                           // Pickup Location in WKT
                    LocalDateTime.now(),                 // Requested time
                    RideRequestStatus.PENDING.toString(), // RideRequest Status
                    rider.getId()                        // Rider ID
            );

            // Return a Dummy RideRequestDto if needed
            rideRequest1.setFare(fare);

            List<Drivers> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest1);
//            send notificatio to all drivers for this request

            rideRequest1.setPickupLocation((Point) new WKTReader().read(pickupWKT));
            rideRequest1.setDropOffLocation((Point) new WKTReader().read(dropOffWKT));

            RideRequestDto response =  modelMapper.map(rideRequest1, RideRequestDto.class);

            ApiResponse<RideRequestDto> res = new ApiResponse<>(response);

            return ResponseEntity.ok(res);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


















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
