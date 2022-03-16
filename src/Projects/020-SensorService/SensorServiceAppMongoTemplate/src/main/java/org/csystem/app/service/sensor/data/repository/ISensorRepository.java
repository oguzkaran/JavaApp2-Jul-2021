package org.csystem.app.service.sensor.data.repository;

import org.csystem.app.service.sensor.data.entity.Sensor;

public interface ISensorRepository {
    Iterable<Sensor> findAll();
    Iterable<Sensor> findByGreater(double value);
    Iterable<Sensor> findByLess(double value);
    Iterable<Sensor> findBetween(double min, double max);
}
