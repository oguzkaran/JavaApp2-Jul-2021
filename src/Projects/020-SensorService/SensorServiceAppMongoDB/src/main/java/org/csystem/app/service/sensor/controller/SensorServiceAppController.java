package org.csystem.app.service.sensor.controller;


import org.csystem.app.service.sensor.data.entity.Sensor;
import org.csystem.app.service.sensor.service.SensorAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sensors")
public class SensorServiceAppController {
    private final SensorAppService m_sensorAppService;

    public SensorServiceAppController(SensorAppService sensorAppService)
    {
        m_sensorAppService = sensorAppService;
    }

    @GetMapping("all")
    public Iterable<Sensor> findAll()
    {
        return m_sensorAppService.findAllSensors();
    }

    @GetMapping("sensor")
    public Iterable<Sensor> findSensorsBetween(double min, double max)
    {
        return m_sensorAppService.findSensorsBetween(min, max);
    }
}
