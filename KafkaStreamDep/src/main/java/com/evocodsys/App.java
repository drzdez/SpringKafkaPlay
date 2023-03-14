package com.evocodsys;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    
     private static final Logger logger = LogManager.getLogger(App.class);

    
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
    
    @Bean
    public java.util.function.Consumer<KStream<Object, String>> process() {
        return input ->
                input.foreach((key, value) -> {
                    logger.info("Key: " + key + " Value: " + value);
                });
    }    
}
