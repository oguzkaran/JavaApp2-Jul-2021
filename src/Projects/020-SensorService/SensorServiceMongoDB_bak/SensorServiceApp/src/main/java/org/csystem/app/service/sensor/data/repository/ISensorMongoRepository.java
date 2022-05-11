package org.csystem.app.service.sensor.data.repository;

import org.csystem.app.service.sensor.data.entity.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ISensorMongoRepository extends MongoRepository<Sensor, String> {
    Iterable<Sensor> findByName(String name);

    Iterable<Sensor> findByData(double value);

    Sensor findFirstByName(String name);

    Iterable<Sensor> findByHost(String host);

    @Query("{data: {$gt: ?0}}")
    Iterable<Sensor> findByGreater(double value);

    @Query("{data: {$lt: ?0}}")
    Iterable<Sensor> findByLess(double value);

    @Query("{data: {$gte:?0, $lte:?1}}")
    Iterable<Sensor> findBetween(double min, double max);
}
