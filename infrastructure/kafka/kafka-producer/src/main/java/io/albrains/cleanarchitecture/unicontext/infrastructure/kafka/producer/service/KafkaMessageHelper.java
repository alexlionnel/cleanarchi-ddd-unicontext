package io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.producer.service;

import org.springframework.kafka.support.SendResult;

import java.util.function.BiConsumer;

public final class KafkaMessageHelper {

    public static <T, E extends Throwable> BiConsumer<SendResult<String, T>, E> getKafkaCallback(
            String responseTopicName,
            T avroModel,
            String avroModelName) {
        return new ResponseFeatureCallback<>(responseTopicName, avroModel, avroModelName);
    }
}
