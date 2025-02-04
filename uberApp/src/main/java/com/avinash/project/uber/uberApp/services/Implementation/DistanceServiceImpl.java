package com.avinash.project.uber.uberApp.services.Implementation;

import com.avinash.project.uber.uberApp.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        return 0;
    }
}
