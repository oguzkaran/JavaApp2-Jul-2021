package com.borasahin.service.sensor.data.repository;

import com.borasahin.service.sensor.data.entity.SensorData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISensorDataRepository extends CrudRepository<SensorData, Long> {

}
