package io.albrains.cleanarchitecture.unicontext.queuepublisher;

import io.albrains.cleanarchitecture.unicontext.core.application.port.output.AccountOpenedMessagePublisher;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.event.AccountOpened;
import io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.model.avro.BankAccountOpenedAvroModel;
import io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.producer.service.KafkaMessageHelper;
import io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.producer.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OpenedBankAccountMessagePublisher implements AccountOpenedMessagePublisher {

    private final KafkaProducer<String, BankAccountOpenedAvroModel> kafkaProducer;

    @Override
    public void publishEvent(AccountOpened accountOpened) {
        log.info("Sending message...");
        kafkaProducer.send(
                "topicname",
                accountOpened.getAccountId().getValue().toString(),
                null,
                KafkaMessageHelper.buildKafkaCallback(
                        "topicname",
                        null,
                        BankAccountOpenedAvroModel.getClassSchema().getName()
                )
        );
    }
}
