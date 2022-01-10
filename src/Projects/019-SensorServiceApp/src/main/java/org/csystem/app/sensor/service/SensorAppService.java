package org.csystem.app.sensor.service;

import org.csystem.app.sensor.data.dal.SensorServiceHelper;
import org.csystem.app.sensor.data.entity.Sensor;
import org.csystem.app.sensor.dto.SensorDTO;
import org.csystem.app.sensor.mapper.ISensorDataMapper;
import org.csystem.app.sensor.mapper.ISensorMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.csystem.util.data.DatabaseUtil.*;

@Service
public class SensorAppService {
    private final SensorServiceHelper m_sensorServiceHelper;
    private final ISensorMapper m_sensorMapper;
    private final ISensorDataMapper m_sensorDataMapper;

    private static <T, R> List<R> mapToList(Iterable<T> iterable, Function<? super T, R> func, boolean parallel) //Şimdilik Kütüphanede olduğunu varsayınız
    {
        return StreamSupport.stream(iterable.spliterator(), parallel).map(func).collect(Collectors.toList());
    }

    private Optional<SensorDTO> findSensorByNameCallback(String name)
    {
        var so = m_sensorServiceHelper.findSensorByName(name);

        return Optional.ofNullable(m_sensorMapper.toSensorDTO(so.isEmpty() ? null : so.get()));
    }

    private List<SensorDTO> findSensorByNameContainsCallback(String text)
    {
        return mapToList(m_sensorServiceHelper.findSensorByNameContains(text), m_sensorMapper::toSensorDTO, false);
    }

    public SensorAppService(SensorServiceHelper sensorServiceHelper, ISensorMapper sensorMapper, ISensorDataMapper sensorDataMapper)
    {
        m_sensorServiceHelper = sensorServiceHelper;
        m_sensorMapper = sensorMapper;
        m_sensorDataMapper = sensorDataMapper;
    }

    public Optional<SensorDTO> findSensorByName(String name)
    {
        return doWorkForService(() -> findSensorByNameCallback(name), "SensorAppService.findSensorByName");
    }

    public Iterable<SensorDTO> findSensorByNameContains(String text)
    {
        return doWorkForService(() -> findSensorByNameContainsCallback(text), "SensorAppService.findSensorByName");
    }



    //...
}
