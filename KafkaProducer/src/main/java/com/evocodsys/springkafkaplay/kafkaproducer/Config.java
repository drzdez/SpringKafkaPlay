/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.evocodsys.springkafkaplay.kafkaproducer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value(value = "${com.springkafkaz.topic}") 
    private String kafkaTopicz;
    
    public String getKafkaTopicz() {
        return this.kafkaTopicz;
    }
  
  
}
