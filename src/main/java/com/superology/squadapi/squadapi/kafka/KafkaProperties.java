package com.superology.squadapi.squadapi.kafka;

import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

public class KafkaProperties {
    private static final String KAFKA_BROKER_IP = "172.22.0.1:19092";
    private static final String AUTO_COMMIT = "false";
    private static final String OFFSET_RESET = "earliest";

    public static Properties getProperties(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", KAFKA_BROKER_IP);
        properties.put("enable.auto.commit", AUTO_COMMIT);
        properties.put("auto.offset.reset", OFFSET_RESET);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }

    public static Properties getProperties(String keyDeserializer, String valueDeserializer){
        Properties properties = getProperties();
        properties.put("key.deserializer", keyDeserializer);
        properties.put("value.deserializer", valueDeserializer);
        return properties;
    }

    public static Properties getProperties(String valueDeserializer){
        Properties properties = getProperties();
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", valueDeserializer);
        return properties;
    }

}
