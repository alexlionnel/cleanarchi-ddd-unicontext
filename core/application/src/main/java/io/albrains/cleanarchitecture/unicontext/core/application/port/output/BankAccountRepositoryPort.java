package io.albrains.cleanarchitecture.unicontext.core.application.port.output;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountNumber;

import java.util.Optional;

public interface BankAccountRepositoryPort {
    Optional<BankAccount> find(AccountNumber accountNumber);

    BankAccount findRequired(AccountNumber accountNumber);

    BankAccount add(BankAccount bankAccount);

    void update(BankAccount bankAccount);
}
