package org.csystem.app.sensor.dto;

public class SensorDataDTO {
    private double m_data;
    private String m_dataDateTime;

    public double getData()
    {
        return m_data;
    }

    public void setData(double data)
    {
        m_data = data;
    }

    public String getDataDateTime()
    {
        return m_dataDateTime;
    }

    public void setDataDateTime(String dataDateTime)
    {
        m_dataDateTime = dataDateTime;
    }
}
