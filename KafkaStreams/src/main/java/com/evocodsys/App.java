package com.evocodsys;


import java.util.function.Function;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsStateStore;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsStateStoreProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
//@EnableKafkaStreams 
public class App {
    
     private static final Logger logger = LogManager.getLogger(App.class);

    
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
 
    
    /*
static class MyTransformer implements Transformer<Object, String, KStream<Object, String>> {

  private ProcessorContext context;

  @Override
  public void init(ProcessorContext context) {
    this.context = context;
  }

  @Override
  public KStream<Object, String> transform(Object value, String v1) {
    return (KStream<Object, String>)value;
  }

  @Override
  public void close() {
  }



}    
*/

    @Bean
    public StoreBuilder myStore() {
        return Stores.keyValueStoreBuilder(
            Stores.persistentKeyValueStore("my-store"), Serdes.String(),
            Serdes.String());
}

    
    @Bean
    public Function<KStream<String, String>, KStream<String, String>> process() {
        return input ->
                input.peek((a, b) -> {
                    logger.info("Key: " + a + " Value: " + b);
                })
        
                .transform(() -> new Transformer<String,String,KeyValue<String,String>>(){

                    private ProcessorContext context;

                    @Override
                      public void init(ProcessorContext context) {
                        this.context = context;
                        logger.info("---- init");
                    }

                    @Override
                    public KeyValue<String,String> transform(String aa,String bb) {
                        logger.info("++++ Key: " + aa + " Value: " + bb);
                        return new KeyValue<>(aa, bb); 
                        
                    }
                   
                    @Override
                      public void close() {
                        logger.info("---- close");
                    }
                }, "my-store");

                
                        
    }    
    
    

}
