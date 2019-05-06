package com.example.springbootkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 18:06
 * @Version 1.0
 */
@Component
public class KafkaConsumer {
    /**
     * 监听seckill主题,有消息就读取a
     * @param record
     */
    @KafkaListener(topics = {"stream.rws.td.comparison"})
    public void receiveMessage(ConsumerRecord<?, ?> record){
        //收到通道的消息之后执行秒杀操作
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        System.out.println(kafkaMessage.get());
    }
}
