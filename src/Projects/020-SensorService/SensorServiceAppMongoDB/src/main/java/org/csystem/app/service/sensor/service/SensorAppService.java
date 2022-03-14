package org.csystem.app.service.sensor.service;

import org.csystem.app.service.sensor.data.dal.SensorServiceAppHelper;
import org.csystem.app.service.sensor.data.entity.Sensor;
import org.springframework.stereotype.Service;

@Service
public class SensorAppService {
    private final SensorServiceAppHelper m_sensorServiceAppHelper;

    public SensorAppService(SensorServiceAppHelper sensorServiceAppHelper)
    {
        m_sensorServiceAppHelper = sensorServiceAppHelper;
    }

    public Iterable<Sensor> findAllSensors()
    {
        return m_sensorServiceAppHelper.findAllSensors();
    }


    public Iterable<Sensor> findSensorsBetween(double min, double max)
    {
        return m_sensorServiceAppHelper.findSensorsBetween(min, max);
    }
}
