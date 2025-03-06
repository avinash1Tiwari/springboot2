package web.app.uber.dto;

public class PointDto {
    private double[] coordinates;
    private String type = "Point";

    public PointDto(double[] coordinates){
        this.coordinates = coordinates;
    }
}
