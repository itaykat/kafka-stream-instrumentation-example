package com.example;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

public class KafkaHelloStream {
  private static final AtomicBoolean appCjhecker = new AtomicBoolean(false);

  public static void main(String[] args) throws InterruptedException {
    Map<String, String> envs = System.getenv();
    System.out.println("property: " + envs.get("STREAMS_BOOTSTRAP_SERVERS"));

    StreamsBuilder builder = new StreamsBuilder();
    KStream<Void, String> stream = builder.stream("hello");
    stream.foreach(
        (key, value) -> {
          System.out.println("hello, " + value);
        });
    KafkaStreams streams = new KafkaStreams(builder.build(), creteKafkaConfig(envs));
    streams.start();
    Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
  }

  private static Properties creteKafkaConfig(Map<String, String> envs) {
    Properties config = new Properties();
    config.put(StreamsConfig.APPLICATION_ID_CONFIG, "hello-stream");
    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, envs.get("STREAMS_BOOTSTRAP_SERVERS"));
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Void().getClass());
    config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    return config;
  }
}
