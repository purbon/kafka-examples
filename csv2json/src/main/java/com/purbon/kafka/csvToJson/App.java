package com.purbon.kafka.csvToJson;

public class App {

  public static void main(String [] args) throws Exception {

    String file = args[0];
    String topic = args[1];

    try (KafkaRawCallbackProcessor processor = new KafkaRawCallbackProcessor(topic)) {
      try (CSVFileReader reader = new CSVFileReader()) {
        reader.process(file, processor);
      }
    }

  }
}
