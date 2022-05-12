package com.superology.squadapi.squadapi.kafka;

import com.superology.squadapi.squadapi.deserializators.ModelDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public abstract class AbstractKafkaConsumer<T> extends Thread{
    @Autowired
    protected JpaRepository<T, Long> repository;

    private boolean active = true;

    private String topicName;

    private Class<T> cls;

    public AbstractKafkaConsumer(String topicName, Class<T> cls){
        this.topicName = topicName;
        this.cls = cls;
    }

    abstract protected void afterReadHook(T value);

    public static Map<String, List<PartitionInfo>> listKafkaTopics(){
        Map<String, List<PartitionInfo>> topicMap = null;
        Properties properties = KafkaProperties.getProperties();
        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);){
            topicMap = consumer.listTopics();
        }
        return topicMap;
    }


    @Override
    public void run() {
        Deserializer<T> deserializer = (Deserializer<T>) new ModelDeserializer<>().setCls(cls);
        Properties properties = KafkaProperties.getProperties(deserializer.getClass().getName());

        try (KafkaConsumer<String, T> consumer = new KafkaConsumer<>(properties)) {
            List<TopicPartition> topicPartitions = consumer.partitionsFor(topicName).
                    stream().
                    map(p -> new TopicPartition(p.topic(), p.partition())).
                    collect(Collectors.toList());
            consumer.assign(topicPartitions);
            consumer.seekToBeginning(consumer.assignment());
            while(active){
                ConsumerRecords<String, T> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, T> record : records) {
                    T current = record.value();
                    afterReadHook(current);
                    try{
                        repository.save(current);
                    }
                    catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                }
            }
        }
    }
}
