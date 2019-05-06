package com.example.springbootkafka.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 19:35
 * @Version 1.0
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServers;
    @Value("${spring.kafka.producer.batch-size}")
    private Integer batchSize;
    @Value("${spring.kafka.producer.retries}")
    private Integer retries;
    @Value("${spring.kafka.producer.buffer-memory}")
    private Integer bufferMemory;
    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;
    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    public Map<String,Object> producerConfigs(){
        Map<String, Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
        map.put(ProducerConfig.BATCH_SIZE_CONFIG,batchSize);
        map.put(ProducerConfig.RETRIES_CONFIG,retries);
        map.put(ProducerConfig.BUFFER_MEMORY_CONFIG,bufferMemory);
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,keySerializer);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,valueSerializer);
        return map;
    }
    public ProducerFactory<String,String> producerConfig(){
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }
    @Bean(value = "kafkaTemplate")
    public KafkaTemplate<String,String> kafkaTemplate(){
        return new KafkaTemplate<>(producerConfig());
    }
}
