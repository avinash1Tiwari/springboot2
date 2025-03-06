package com.avinash.project.uber.uberApp.services.Implementation;

import com.avinash.project.uber.uberApp.services.DistanceService;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Data


@RequiredArgsConstructor
@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
//        called the third part api OSRM to fetch the distance
        return 0;
    }
}
