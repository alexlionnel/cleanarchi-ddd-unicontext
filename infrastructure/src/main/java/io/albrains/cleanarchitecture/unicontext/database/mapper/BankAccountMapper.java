package io.albrains.cleanarchitecture.unicontext.database.mapper;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.database.entity.BankAccountEntity;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {

    public BankAccountEntity bankAccountToBankAccountEntity(BankAccount bankAccount) {
        return null;
    }

    public BankAccount bankAccountEntityToBankAccount(BankAccountEntity bankAccountEntity) {
        return null;
    }
}
