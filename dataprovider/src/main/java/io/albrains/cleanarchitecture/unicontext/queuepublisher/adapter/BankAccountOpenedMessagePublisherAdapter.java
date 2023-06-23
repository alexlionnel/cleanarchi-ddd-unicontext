package io.albrains.cleanarchitecture.unicontext.queuepublisher.adapter;

import io.albrains.cleanarchitecture.unicontext.core.application.port.output.BankAccountOpenedMessagePublisher;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.event.AccountOpened;
import io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.model.avro.BankAccountOpenedAvroModel;
import io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.producer.service.KafkaMessageHelper;
import io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.producer.service.KafkaProducer;
import io.albrains.cleanarchitecture.unicontext.queuepublisher.mapper.BankAccountMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BankAccountOpenedMessagePublisherAdapter implements BankAccountOpenedMessagePublisher {

    private final KafkaProducer<String, BankAccountOpenedAvroModel> kafkaProducer;
    private final BankAccountMessageMapper bankAccountMessageMapper;

    @Override
    public void publishEvent(AccountOpened accountOpened) {
        var bankAccountOpenedAvroModel = bankAccountMessageMapper.bankAccountOpenedEventToBankAccountOpenedAvroModel(accountOpened);
        log.info("Sending message bankAccountOpenedAvroModel with accountNumber: {}", accountOpened.getAccountNumber().getValue().toString());
        kafkaProducer.send(
                "topicname",
                accountOpened.getAccountId().getValue().toString(),
                bankAccountOpenedAvroModel,
                KafkaMessageHelper.buildKafkaCallback(
                        "topicname",
                        bankAccountOpenedAvroModel,
                        BankAccountOpenedAvroModel.getClassSchema().getName()
                )
        );
    }
}
