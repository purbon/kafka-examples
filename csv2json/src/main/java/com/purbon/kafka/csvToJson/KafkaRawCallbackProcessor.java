package com.purbon.kafka.csvToJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class KafkaRawCallbackProcessor implements RawCallbackProcessor, AutoCloseable {

  private final String topic;
  private AppKafkaProducer producer;
  private final ObjectMapper mapper;

  public KafkaRawCallbackProcessor(String topic) {
    this(topic, new AppKafkaProducer());
  }

  public KafkaRawCallbackProcessor(String topic, String producerConfigFile) {
    this(topic);
    if (producerConfigFile == null || producerConfigFile.isEmpty()) {
      this.producer = new AppKafkaProducer();
    } else {
      this.producer = new AppKafkaProducer(parseProducerConfigFile(producerConfigFile));
    }
  }

  public KafkaRawCallbackProcessor(String topic, AppKafkaProducer producer) {
    this.topic = topic;
    this.producer = producer;
    this.mapper = new ObjectMapper();
  }

  @Override
  public void close() throws Exception {
    producer.close();
  }

  @Override
  public void call(Map<String, String> map) {
    try {
      String value = mapper.writeValueAsString(map);
      producer.send(topic, value);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  private Properties parseProducerConfigFile(String configFile) {
    Properties prop = new Properties();
    try {
      prop.load(new FileInputStream(configFile));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return prop;
  }
}
