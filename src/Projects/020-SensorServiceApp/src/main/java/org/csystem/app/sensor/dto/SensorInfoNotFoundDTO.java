package org.csystem.app.sensor.dto;

public class SensorInfoNotFoundDTO {
    public String name;
    public String message;

    public SensorInfoNotFoundDTO(String name, String message)
    {
        this.name = name;
        this.message = message;
    }
}
