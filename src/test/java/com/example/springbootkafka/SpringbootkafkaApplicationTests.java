package com.example.springbootkafka;

import com.alibaba.fastjson.JSON;
import com.example.springbootkafka.producer.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootkafkaApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    //发送消息方法
    @Test
    public void send() throws InterruptedException {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", JSON.toJSONString(message));
        for(int i=0;i<=10;i++){
            kafkaTemplate.send("zhisheng",String.valueOf(i), JSON.toJSONString(message));
        }
    }

    @Autowired
    private KafkaConsumer kafkaConsumer;
    @Test
    public void bb(){
        HashSet<String> strings = new HashSet<>();
        strings.add("zhisheng");
        kafkaConsumer.subscribe(strings);
        while (true){

            ConsumerRecords<String,String> poll = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : poll) {
                System.out.println(record.partition()+" | "+record.offset()+"\t"+record.key()+":"+record.value()+" ");
            }
        }
    }

}
