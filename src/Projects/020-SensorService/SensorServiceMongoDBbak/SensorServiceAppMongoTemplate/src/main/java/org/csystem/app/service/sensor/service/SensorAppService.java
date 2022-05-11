package org.csystem.app.service.sensor.service;

import org.csystem.app.service.sensor.data.dal.SensorServiceAppHelper;
import org.csystem.app.service.sensor.data.dto.SensorInfoDTO;
import org.csystem.app.service.sensor.mapper.ISensorMapper;
import org.springframework.stereotype.Service;

import static org.csystem.util.collection.CollectionUtil.toList;

@Service
public class SensorAppService {
    private final SensorServiceAppHelper m_sensorServiceAppHelper;
    private final ISensorMapper m_sensorMapper;

    public SensorAppService(SensorServiceAppHelper sensorServiceAppHelper, ISensorMapper sensorMapper)
    {
        m_sensorServiceAppHelper = sensorServiceAppHelper;
        m_sensorMapper = sensorMapper;
    }

    public Iterable<SensorInfoDTO> findAllSensors()
    {
        return toList(m_sensorServiceAppHelper.findAllSensors(), m_sensorMapper::toSensorInfoDTO);
    }

    public Iterable<SensorInfoDTO> findSensorsByGreater(double value)
    {
        return toList(m_sensorServiceAppHelper.findSensorsByGreater(value), m_sensorMapper::toSensorInfoDTO);
    }

    public Iterable<SensorInfoDTO> findSensorsByLess(double value)
    {
        return toList(m_sensorServiceAppHelper.findSensorsByLess(value), m_sensorMapper::toSensorInfoDTO);
    }

    public Iterable<SensorInfoDTO> findSensorsBetween(double min, double max)
    {
        return toList(m_sensorServiceAppHelper.findSensorsBetween(min, max), m_sensorMapper::toSensorInfoDTO);
    }
}
