package org.csystem.app.service.sensor.data.repository;

import org.csystem.app.service.sensor.data.entity.Sensor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SensorRepository implements ISensorRepository {
    private final MongoTemplate m_mongoTemplate;

    public SensorRepository(MongoTemplate mongoTemplate)
    {
        m_mongoTemplate = mongoTemplate;
    }

    @Override
    public Iterable<Sensor> findAll()
    {
        return m_mongoTemplate.findAll(Sensor.class, "sensors");
    }

    @Override
    public Iterable<Sensor> findByGreater(double value)
    {
        var query = new Query();

        query.addCriteria(Criteria.where("data").gt(value));

        return m_mongoTemplate.find(query, Sensor.class, "sensors");
    }

    @Override
    public Iterable<Sensor> findByLess(double value)
    {
        var query = new Query();

        query.addCriteria(Criteria.where("data").lt(value));

        return m_mongoTemplate.find(query, Sensor.class, "sensors");
    }

    @Override
    public Iterable<Sensor> findBetween(double min, double max)
    {
        var query = new Query();

        query.addCriteria(new Criteria().andOperator(Criteria.where("data").gte(min), Criteria.where("data").lte(max)));

        return m_mongoTemplate.find(query, Sensor.class, "sensors");
    }
}
