package com.example.sum_by_camel_kafka.producer;


import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Producer extends RouteBuilder {

    @Autowired
    CamelContext camelContext;

    public Integer randomNumber(){
        while (true){
            Random random = new Random();
            int i = random.nextInt(100);
            return i;
        }
    }

    @Override
    public void configure() throws Exception {

        camelContext.start();

        from("timer://foo?period=10000")
                .bean("producer" , "randomNumber")
                .to("kafka:sum?brokers=localhost:9092");

    }
}
