package com.example.sum_by_camel_kafka.consumer;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class Consumer extends RouteBuilder {

    @Autowired
    ConsumerTemplate consumerTemplate;
    Logger logger = Logger.getLogger(Consumer.class.getName());
    private static List<Integer> list = new ArrayList<>();

    public void getNumbersInTopic() {

        while (true) {

            Object body = consumerTemplate.receiveBody("kafka:sum?brokers=localhost:9092");

            int number = Integer.parseInt((String) body);
//            System.out.println(number);
            list.add(number);

        }

    }

    public void logSum() {

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i);
        }

        logger.info("Sum is : " + sum);
    }


    @Override
    public void configure() {

        from("kafka:sum?brokers=localhost:9092")
                .delay(60000)
                .bean("consumer", "logSum");

    }
}
