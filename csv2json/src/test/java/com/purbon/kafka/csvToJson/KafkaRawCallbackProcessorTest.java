package com.purbon.kafka.csvToJson;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class KafkaRawCallbackProcessorTest {

  @Mock
  AppKafkaProducer producer;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();
  @Before
  public void setup() {

  }

  @Test
  public void testKafkaProducing() {
    KafkaRawCallbackProcessor processor = new KafkaRawCallbackProcessor("my-topic", producer);

    Map<String, String> dummyMap = new HashMap<>();
    dummyMap.put("id", "1234");
    processor.call(dummyMap);

    verify(producer, atLeast(1)).send(eq("my-topic"), anyString());

  }
}
