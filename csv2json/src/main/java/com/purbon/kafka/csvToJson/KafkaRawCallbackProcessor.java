package com.purbon.kafka.csvToJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

public class KafkaRawCallbackProcessor implements RawCallbackProcessor, AutoCloseable {

  private final String topic;
  private final AppKafkaProducer producer;
  private final ObjectMapper mapper;

  public KafkaRawCallbackProcessor(String topic) {
    this.producer = new AppKafkaProducer();
    this.mapper = new ObjectMapper();
    this.topic = topic;
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
}
