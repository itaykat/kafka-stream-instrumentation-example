package com.example;

import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaHelloProducer {

    public class SimpleProducer {
        public void main(String[] args) throws InterruptedException {
            // Generate total consecutive events starting with msgId
            long msgId = Math.round(Math.random() * Integer.MAX_VALUE);

            // Set up client Java properties
            Properties props = new Properties();
            Map<String, String> envs = System.getenv();

            props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, envs.get("STREAMS_BOOTSTRAP_SERVERS"));
            props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                    StringSerializer.class.getName());
            props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                    StringSerializer.class.getName());
            props.setProperty(ProducerConfig.ACKS_CONFIG, "1");

            try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
                while (true) {
                    String key = Long.toString(msgId++);
                    String msg = "testing";
                    try {
                        ProducerRecord<String, String> data = new ProducerRecord<String, String>("hello", key,
                                msg);
                        producer.send(data);
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
