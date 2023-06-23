package io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.producer.service;

import org.springframework.kafka.support.SendResult;

import java.util.function.BiConsumer;

public final class KafkaMessageHelper {

    public static <T, E extends Throwable> BiConsumer<SendResult<String, T>, E> buildKafkaCallback(
            String topicName,
            T avroModel,
            String avroModelName) {
        return new ResponseFeatureCallback<>(topicName, avroModel, avroModelName);
    }
}
