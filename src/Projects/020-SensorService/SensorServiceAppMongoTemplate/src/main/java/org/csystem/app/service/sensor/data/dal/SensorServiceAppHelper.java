package org.csystem.app.service.sensor.data.dal;

import org.csystem.app.service.sensor.data.entity.Sensor;
import org.csystem.app.service.sensor.data.repository.ISensorRepository;
import org.springframework.stereotype.Component;

@Component
public class SensorServiceAppHelper {
    private final ISensorRepository m_sensorRepository;

    public SensorServiceAppHelper(ISensorRepository sensorRepository)
    {
        m_sensorRepository = sensorRepository;
    }

    public Iterable<Sensor> findAllSensors()
    {
        return m_sensorRepository.findAll();
    }

    public Iterable<Sensor> findSensorsByGreater(double value)
    {
        return m_sensorRepository.findByGreater(value);
    }

    public Iterable<Sensor> findSensorsByLess(double value)
    {
        return m_sensorRepository.findByLess(value);
    }

    public Iterable<Sensor> findSensorsBetween(double min, double max)
    {
        return m_sensorRepository.findBetween(min, max);
    }

    //...
}
