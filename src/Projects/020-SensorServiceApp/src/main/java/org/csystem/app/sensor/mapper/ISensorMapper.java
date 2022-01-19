package org.csystem.app.sensor.mapper;

import org.csystem.app.sensor.data.entity.Sensor;
import org.csystem.app.sensor.dto.SensorDTO;
import org.csystem.app.sensor.dto.SensorsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(implementationName = "SensorMapperImpl", componentModel = "spring")
public interface ISensorMapper {
    Sensor toSensor(SensorDTO sensorDTO);
    @Mapping(source = "sensorData", target = "data")
    SensorDTO toSensorDTO(Sensor sensor);
    default SensorsDTO toSensorsDTO(List<SensorDTO> sensors)
    {
        var result = new SensorsDTO();
        result.sensors = sensors;

        return result;
    }
}
