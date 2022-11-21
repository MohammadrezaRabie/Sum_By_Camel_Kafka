package com.example.sum_by_camel_kafka;

import com.example.sum_by_camel_kafka.consumer.Consumer;
import com.example.sum_by_camel_kafka.producer.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SumByCamelKafkaApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SumByCamelKafkaApplication.class, args);

        Producer producer = context.getBean(Producer.class);
        Consumer consumer = context.getBean(Consumer.class);

        producer.configure();

        consumer.getNumbersInTopic();
    }

}
