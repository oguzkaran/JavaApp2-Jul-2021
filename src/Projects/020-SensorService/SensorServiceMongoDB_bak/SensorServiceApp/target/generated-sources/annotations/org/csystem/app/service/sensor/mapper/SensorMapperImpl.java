package org.csystem.app.service.sensor.mapper;

import javax.annotation.processing.Generated;
import org.csystem.app.service.sensor.data.dto.SensorInfoDTO;
import org.csystem.app.service.sensor.data.entity.Sensor;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-16T21:23:11+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class SensorMapperImpl implements ISensorMapper {

    @Override
    public Sensor toSensor(SensorInfoDTO sensorInfoDTO) {
        if ( sensorInfoDTO == null ) {
            return null;
        }

        Sensor sensor = new Sensor();

        sensor.name = sensorInfoDTO.name;
        sensor.data = sensorInfoDTO.data;
        sensor.host = sensorInfoDTO.host;
        sensor.port = sensorInfoDTO.port;
        sensor.registerDate = sensorInfoDTO.registerDate;

        return sensor;
    }

    @Override
    public SensorInfoDTO toSensorInfoDTO(Sensor sensor) {
        if ( sensor == null ) {
            return null;
        }

        SensorInfoDTO sensorInfoDTO = new SensorInfoDTO();

        sensorInfoDTO.name = sensor.name;
        sensorInfoDTO.data = sensor.data;
        sensorInfoDTO.host = sensor.host;
        sensorInfoDTO.port = sensor.port;
        sensorInfoDTO.registerDate = sensor.registerDate;

        return sensorInfoDTO;
    }
}
