package org.csystem.app.sensor.controller;

import org.csystem.app.sensor.dto.SensorDTO;
import org.csystem.app.sensor.dto.SensorInfoNotFoundDTO;
import org.csystem.app.sensor.dto.SensorsDTO;
import org.csystem.app.sensor.service.SensorAppService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("api/sensors")
public class SensorController {
    private final SensorAppService m_sensorAppService;

    public SensorController(SensorAppService sensorAppService)
    {
        m_sensorAppService = sensorAppService;
    }

    @GetMapping("sensor/all")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SYSTEM')")
    public List<SensorDTO> findAllSensors()
    {
        return m_sensorAppService.findAllSensors();
    }

    @GetMapping("sensor/name")
    @PreAuthorize("hasAuthority('ROLE_SYSTEM')")
    public Object findSensorByName(String name)
    {
        var so = m_sensorAppService.findSensorByName(name);

        return so.isPresent() ? so : new SensorInfoNotFoundDTO(name, "Sensor not found");
    }

    @GetMapping("sensor/contains")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public Iterable<SensorDTO> findSensorsByNameContains(String text)
    {
        return m_sensorAppService.findSensorByNameContains(text);
    }

    @GetMapping("sensor/detail/contains")
    @Secured({"ROLE_SYSTEM", "ROLE_ADMIN"})
    public SensorsDTO findSensorsByNameContainsDetail(String text)
    {
        return m_sensorAppService.findSensorByNameContainsDetail(text);
    }
}
