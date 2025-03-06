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
public class DistanceServiceImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        return 0;
    }
}
