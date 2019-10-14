package com.purbon.kafka.csvToJson;

import java.util.Map;

public interface RawCallbackProcessor {
  void call(Map<String, String> toMap);
}
