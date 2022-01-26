package org.csystem.app.service.sensor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.Set;

public class SensorDTO {
    private String m_name;
    private LocalDate m_registerDate;
    private boolean m_active;
    private Set<SensorDataDTO> m_data;

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public LocalDate getRegisterDate()
    {
        return m_registerDate;
    }

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public void setRegisterDate(LocalDate registerDate)
    {
        m_registerDate = registerDate;
    }

    public boolean isActive()
    {
        return m_active;
    }

    public void setActive(boolean active)
    {
        m_active = active;
    }

    public Set<SensorDataDTO> getData()
    {
        return m_data;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public void setData(Set<SensorDataDTO> data)
    {
        m_data = data;
    }
}
