package com.superology.squadapi.squadapi.deserializators;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@NoArgsConstructor
public class ModelDeserializer<T> implements Deserializer<T> {
    private ObjectMapper objectMapper = new ObjectMapper();
    public static Class<?> CLS;

    public Deserializer<T>  setCls(Class<?> cls){
        ModelDeserializer.CLS = cls;
        return this;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public T deserialize(String s, byte[] data) {
        try {
            if (data == null){
                return null;
            }
            Class<T> cls = (Class<T>) CLS;
            return objectMapper.readValue(new String(data, "UTF-8"), cls);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }

    @Override
    public void close() {}
}
