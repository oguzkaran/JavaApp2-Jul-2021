package org.csystem.app.service.sensor.data.repository;

import org.csystem.app.service.sensor.data.entity.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISensorRepository extends CrudRepository<Sensor, Integer> {
    //@Query("select s from Sensor s where s.name like :name")
    Optional<Sensor> findByName(@Param("name") String name);
    Iterable<Sensor> findByNameContains(@Param("text") String text);
}
