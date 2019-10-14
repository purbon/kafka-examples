package com.purbon.kafka.csvToJson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVFileReader implements AutoCloseable {

  public void process(String file, RawCallbackProcessor processor) throws IOException {
    Reader in = new FileReader(file);
    process(in, processor);
  }

  public void process(Reader reader, RawCallbackProcessor processor) throws IOException {
    Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(reader);
    for (CSVRecord record : records) {
      processor.call(record.toMap());
    }
  }


  @Override
  public void close() throws Exception {

  }
}
