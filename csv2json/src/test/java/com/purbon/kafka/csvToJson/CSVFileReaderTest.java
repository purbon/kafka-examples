package com.purbon.kafka.csvToJson;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class CSVFileReaderTest {

  @Mock
  KafkaRawCallbackProcessor processor;

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Before
  public void setup() {

  }

  @Test
  public void testDataParsing() throws IOException {

    String data = "\"foo\",\"bar\",1,\"zoo\"";
    StringReader reader = new StringReader(data);

    CSVFileReader csvReader = new CSVFileReader();
    csvReader.process(reader, processor);

    verify(processor).call(anyMap());

  }
}
