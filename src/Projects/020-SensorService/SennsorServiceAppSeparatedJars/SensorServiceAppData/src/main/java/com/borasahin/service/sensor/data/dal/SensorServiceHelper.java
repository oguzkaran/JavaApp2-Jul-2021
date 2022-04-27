package com.borasahin.service.sensor.data.dal;

import com.borasahin.service.sensor.data.entity.Sensor;
import com.borasahin.service.sensor.data.repository.ISensorDataRepository;
import com.borasahin.service.sensor.data.repository.ISensorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;

@Component
public class SensorServiceHelper {
    private final ISensorRepository m_sensorRepository;
    private final ISensorDataRepository m_sensorDataRepository;


    public SensorServiceHelper(ISensorRepository sensorRepository, ISensorDataRepository sensorDataRepository)
    {
        m_sensorRepository = sensorRepository;
        m_sensorDataRepository = sensorDataRepository;
    }

    public Iterable<Sensor> findAllSensors() //İleride asenkron yapılacak
    {
        return doWorkForRepository(m_sensorRepository::findAll, "SensorServiceHelper.findAllSensors");
    }

    public Optional<Sensor> findSensorByName(String name)
    {
        return doWorkForRepository(() -> m_sensorRepository.findByName(name), "SensorServiceHelper.findSensorByName");
    }

    public Iterable<Sensor> findSensorByNameContains(String text)
    {
        return doWorkForRepository(() -> m_sensorRepository.findByNameContains(text), "SensorServiceHelper.findSensorByNameContains");
    }

    //...
}
