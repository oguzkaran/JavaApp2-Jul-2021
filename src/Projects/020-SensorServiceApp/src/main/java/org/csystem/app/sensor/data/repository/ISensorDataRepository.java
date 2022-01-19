package org.csystem.app.sensor.data.repository;

import org.csystem.app.sensor.data.entity.SensorData;
import org.springframework.data.repository.CrudRepository;

public interface ISensorDataRepository extends CrudRepository<SensorData, Long> {

}
