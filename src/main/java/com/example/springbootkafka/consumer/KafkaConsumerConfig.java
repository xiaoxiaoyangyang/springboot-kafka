package com.example.springbootkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/6 9:55
 * @Version 1.0
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServers;
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;
    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private int autoCommitInterval;
    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keySerializer;
    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueSerializer;

    public Map<String,Object> consumerConfigs(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ConsumerConfig.GROUP_ID_CONFIG,groupId);
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootStrapServers);
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,autoOffsetReset);
        map.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,enableAutoCommit);
        map.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,autoCommitInterval);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,keySerializer);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,valueSerializer);
        return map;
    }

    public ConsumerFactory<String,String> consumerFactorys(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
    @Bean("kafkaConsumer")
    public KafkaConsumer kafkaConsumer(){
        return new KafkaConsumer(consumerConfigs());
    }
}
