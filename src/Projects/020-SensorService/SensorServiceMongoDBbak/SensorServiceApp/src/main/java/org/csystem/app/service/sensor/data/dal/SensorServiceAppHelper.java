package org.csystem.app.service.sensor.data.dal;

import org.csystem.app.service.sensor.data.entity.Sensor;
import org.csystem.app.service.sensor.data.repository.ISensorMongoRepository;
import org.springframework.stereotype.Component;

@Component
public class SensorServiceAppHelper {
    private final ISensorMongoRepository m_sensorMongoRepository;

    public SensorServiceAppHelper(ISensorMongoRepository sensorMongoRepository)
    {
        m_sensorMongoRepository = sensorMongoRepository;
    }

    public Iterable<Sensor> findAllSensors()
    {
        return m_sensorMongoRepository.findAll();
    }

    public Iterable<Sensor> findSensorsByGreater(double value)
    {
        return m_sensorMongoRepository.findByGreater(value);
    }

    public Iterable<Sensor> findSensorsByLess(double value)
    {
        return m_sensorMongoRepository.findByLess(value);
    }

    public Iterable<Sensor> findSensorsBetween(double min, double max)
    {
        return m_sensorMongoRepository.findBetween(min, max);
    }

    //...
}
