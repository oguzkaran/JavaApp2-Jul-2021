package org.csystem.app.sensor.service;

import org.csystem.app.sensor.data.dal.SensorServiceHelper;
import org.csystem.app.sensor.mapper.ISensorDataMapper;
import org.csystem.app.sensor.mapper.ISensorMapper;
import org.springframework.stereotype.Service;

@Service
public class SensorAppService {
    private final SensorServiceHelper m_sensorServiceHelper;
    private final ISensorMapper m_sensorMapper;
    private final ISensorDataMapper m_sensorDataMapper;

    public SensorAppService(SensorServiceHelper sensorServiceHelper, ISensorMapper sensorMapper, ISensorDataMapper sensorDataMapper)
    {
        m_sensorServiceHelper = sensorServiceHelper;
        m_sensorMapper = sensorMapper;
        m_sensorDataMapper = sensorDataMapper;
    }

    //...
}
