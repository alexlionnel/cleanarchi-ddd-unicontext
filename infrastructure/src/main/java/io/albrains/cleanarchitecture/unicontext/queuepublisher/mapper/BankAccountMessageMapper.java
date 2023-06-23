package io.albrains.cleanarchitecture.unicontext.queuepublisher.mapper;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.event.AccountOpened;
import io.albrains.cleanarchitecture.unicontext.infrastructure.kafka.model.avro.BankAccountOpenedAvroModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class BankAccountMessageMapper {

    public BankAccountOpenedAvroModel bankAccountOpenedEventToBankAccountOpenedAvroModel(AccountOpened accountOpened) {
        return BankAccountOpenedAvroModel.newBuilder()
                .setAccountId(accountOpened.getAccountId().getValue().toString())
                .setAccountNumber(accountOpened.getAccountNumber().getValue().toString())
                .setAmount(accountOpened.getBalance().getMoney().getAmount())
                .setCreatedAt(LocalDateTime.now().toInstant(ZoneOffset.UTC))
                .setFirstName(accountOpened.getAccountHolderName().firstName())
                .setLastName(accountOpened.getAccountHolderName().lastName())
                .build();
    }
}
