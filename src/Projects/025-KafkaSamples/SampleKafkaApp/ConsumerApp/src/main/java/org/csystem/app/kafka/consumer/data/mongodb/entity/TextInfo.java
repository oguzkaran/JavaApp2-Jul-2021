package org.csystem.app.kafka.consumer.data.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document("texts")
public class TextInfo {
    @Id
    public String id;

    @Field("text")
    public String text;

    @Field("dateTime")
    public LocalDateTime dateTime;

    @Field("producerName")
    public String producerName;

    @Field("partition")
    public String partition;

    public TextInfo(String text, String producerName, String partition)
    {
        this.text = text;
        this.dateTime = LocalDateTime.now();
        this.producerName = producerName;
        this.partition = partition;
    }
}
