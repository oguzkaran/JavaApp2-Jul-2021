package org.csystem.app.service.sensor.mapper;

import org.csystem.app.service.sensor.data.dto.SensorInfoDTO;
import org.csystem.app.service.sensor.data.entity.Sensor;
import org.mapstruct.Mapper;

@Mapper(implementationName = "SensorMapperImpl", componentModel = "spring")
public interface ISensorMapper {
    Sensor toSensor(SensorInfoDTO sensorInfoDTO);
    SensorInfoDTO toSensorInfoDTO(Sensor sensor);
}
