package com.purbon.kafka.csvToJson;

public class App {

  public static void main(String [] args) throws Exception {

    String file = args[0];
    String topic = args[1];
    String propsFile = "";
    if (args.length > 2) {
      propsFile = args[2];
    }

    try (KafkaRawCallbackProcessor processor = new KafkaRawCallbackProcessor(topic, propsFile)) {
      try (CSVFileReader reader = new CSVFileReader()) {
        reader.process(file, processor);
      }
    }

  }
}
