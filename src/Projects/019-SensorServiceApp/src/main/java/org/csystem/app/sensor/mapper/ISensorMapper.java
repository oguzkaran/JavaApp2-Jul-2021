package org.csystem.app.sensor.mapper;

import org.csystem.app.sensor.data.entity.Sensor;
import org.csystem.app.sensor.dto.SensorDTO;
import org.mapstruct.Mapper;

@Mapper(implementationName = "SensorMapperImpl", componentModel = "spring")
public interface ISensorMapper {
    Sensor toSensor(SensorDTO sensorDTO);
    SensorDTO toSensorDTO(Sensor sensor);
}
