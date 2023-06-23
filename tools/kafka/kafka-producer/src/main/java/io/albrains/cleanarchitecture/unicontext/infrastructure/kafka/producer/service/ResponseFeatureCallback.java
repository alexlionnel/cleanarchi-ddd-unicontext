package io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.producer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;

import java.util.function.BiConsumer;

@Slf4j
public class ResponseFeatureCallback<T, E extends Throwable> implements BiConsumer<SendResult<String, T>, E> {

    private final String responseTopicName;
    private final T avroModel;
    private final String avroModelName;

    public ResponseFeatureCallback(String responseTopicName, T avroModel, String avroModelName) {
        this.responseTopicName = responseTopicName;
        this.avroModel = avroModel;
        this.avroModelName = avroModelName;
    }

    @Override
    public void accept(SendResult<String, T> result, E exception) {
        if (exception != null) {
            handleFailure(exception);
        } else {
            handleSuccess(result);
        }
    }

    public void handleFailure(E exception) {
        log.error(String.format("Error while sending %s with message %s to topic %s", avroModelName, avroModel.toString(), responseTopicName), exception);
    }

    public void handleSuccess(SendResult<String, T> result) {
        if (result != null) {
            var metadata = result.getRecordMetadata();
            log.info("Received successful response from kafka " +
                     "Topic: {} " +
                     "Partition: {} " +
                     "Offset: {} " +
                     "Timestamp: {}",
                    metadata.topic(), metadata.partition(), metadata.offset(), metadata.timestamp());
        }
    }
}
