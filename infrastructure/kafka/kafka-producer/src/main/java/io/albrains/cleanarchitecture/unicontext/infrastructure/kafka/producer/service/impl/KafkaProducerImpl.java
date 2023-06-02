package io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.producer.service.impl;

import io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.producer.exception.KafkaProducerException;
import io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Component
@Slf4j
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback) {
        log.info("Sending message={} to topic={}", message, topicName);
        try {
            CompletableFuture<SendResult<K, V>> kafkaResultFeature = kafkaTemplate.send(topicName, key, message).completable();
            kafkaResultFeature.whenComplete(callback);
        } catch (KafkaException e) {
            log.error(String.format("Error on kafka producer with key: %s, message: %s and exception: %s", key, message, e.getMessage()), e);
            throw new KafkaProducerException("Error on kafka producer with key: $key, message: $message and exception: ${e.message}");
        }
    }
}
