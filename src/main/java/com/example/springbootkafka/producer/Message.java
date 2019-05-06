package com.example.springbootkafka.producer;

import lombok.Data;

import java.util.Date;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 15:35
 * @Version 1.0
 */
@Data
public class Message {
    private Long id;
    private String msg;
    private Date sendTime;
}
