package com.avinash.project.uber.uberApp.utils;

import com.avinash.project.uber.uberApp.dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {

    public static Point createPoint(PointDto pointDto)
    {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),4326);   /// SRID = 4326 => points to earth's glob

        Coordinate coordinates = new Coordinate(pointDto.getCoordinates()[0],pointDto.getCoordinates()[1] );

        return geometryFactory.createPoint(coordinates);
    }
}
