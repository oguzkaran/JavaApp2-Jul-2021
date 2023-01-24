package org.csystem.app.kafka.consumer.data.mongodb.repository;

import org.csystem.app.kafka.consumer.data.mongodb.entity.TextInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITextInfoRepository extends MongoRepository<TextInfo, Integer> {
}
