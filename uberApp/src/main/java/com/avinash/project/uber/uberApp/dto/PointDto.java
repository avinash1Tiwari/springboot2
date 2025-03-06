package com.avinash.project.uber.uberApp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointDto {
    private double[] coordinates;
    private String type = "Point";

    public PointDto(double[] coordinates){
        this.coordinates = coordinates;
    }

}
