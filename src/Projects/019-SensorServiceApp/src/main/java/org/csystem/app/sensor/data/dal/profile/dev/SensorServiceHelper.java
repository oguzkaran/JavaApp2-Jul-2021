package org.csystem.app.sensor.data.dal.profile.dev;

import org.csystem.app.sensor.data.entity.Sensor;
import org.csystem.app.sensor.data.repository.ISensorDataRepository;
import org.csystem.app.sensor.data.repository.ISensorRepository;
import org.csystem.util.console.Console;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.doWorkForRepository;

@Component("dev.sensorServiceHelper")
@Profile("dev")
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
        Console.writeLine("SensorServiceDevHelper.findAllSensors");
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
