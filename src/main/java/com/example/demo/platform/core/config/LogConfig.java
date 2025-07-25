package com.example.demo.platform.core.config;

import com.example.demo.platform.core.extractor.UserExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.core.DefaultHttpLogFormatter;
import org.zalando.logbook.core.DefaultHttpLogWriter;
import org.zalando.logbook.core.DefaultSink;
import org.zalando.logbook.core.WithoutBodyStrategy;

@Configuration
public class LogConfig {
  @Bean
  @Profile("!prod")
  public HttpLogFormatter httpFormatter() {
    return new DefaultHttpLogFormatter();
  }

  @Bean
  public Logbook logbook(HttpLogFormatter formatter) {
    return Logbook.builder()
        .strategy(new WithoutBodyStrategy())
        .sink(new DefaultSink(formatter, new DefaultHttpLogWriter()))
        .attributeExtractor(new UserExtractor())
        .build();
  }
}
