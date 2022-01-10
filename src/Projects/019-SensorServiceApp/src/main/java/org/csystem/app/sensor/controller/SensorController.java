package org.csystem.app.sensor.controller;


import org.csystem.app.sensor.dto.SensorDTO;
import org.csystem.app.sensor.dto.SensorInfoNotFoundDTO;
import org.csystem.app.sensor.service.SensorAppService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/sensors")
public class SensorController {
    private final SensorAppService m_sensorAppService;

    public SensorController(SensorAppService sensorAppService)
    {
        m_sensorAppService = sensorAppService;
    }

    @GetMapping("/sensor/name")
    public Object findSensorByName(String name)
    {
        var so = m_sensorAppService.findSensorByName(name);

        return so.isPresent() ? so : new SensorInfoNotFoundDTO(name, "Sensor not found");
    }

    @GetMapping("/sensor/contains")
    public Iterable<SensorDTO> findSensorsByNameContains(String text)
    {
        return m_sensorAppService.findSensorByNameContains(text);
    }
}
