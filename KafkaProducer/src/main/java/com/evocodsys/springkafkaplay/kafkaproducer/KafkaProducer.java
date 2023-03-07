/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.evocodsys.springkafkaplay.kafkaproducer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author zdraz
 */
@Service
public class KafkaProducer {

  private static final Logger logger = LogManager.getLogger(KafkaProducer.class);
    
  int iCycles = 0;
  KafkaTemplate<String, String> kafkaTemplate;
  Config kafkaConfig;
  
  @Autowired
  public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate,
          Config kafkaConfig){
      this.kafkaTemplate = kafkaTemplate;
      this.kafkaConfig = kafkaConfig;
  }
    
   @Scheduled(fixedRate=1000)
    public void sched()  {
            logger.info("Running " + iCycles++);
            kafkaTemplate.send(kafkaConfig.getKafkaTopicz(), Integer.toString(iCycles), String.format("test%3d", iCycles));
    }
  
}
