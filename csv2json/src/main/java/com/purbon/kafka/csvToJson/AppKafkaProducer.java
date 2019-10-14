package com.purbon.kafka.csvToJson;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AppKafkaProducer {

  private KafkaProducer<String, String> producer = new KafkaProducer<>(configure());

  private Properties configure() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "my-producer");
    props.put("max.block.ms", 2000);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

    return props;
  }

  public void send(String topic, String value) {

    ProducerRecord<String, String> record = new ProducerRecord<>(topic, value);
    producer.send(record,
        (metadata, error) -> {
          if (error == null) {
            //TODO: No error handler
          } else {
            //TODO: Somehing has happened, error has reported
          }
        }
    );
  }

  public void close() {
    producer.close();
  }


}
