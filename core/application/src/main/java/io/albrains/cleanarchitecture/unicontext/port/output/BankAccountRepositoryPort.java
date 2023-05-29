package io.albrains.cleanarchitecture.unicontext.port.output;

import io.albrains.cleanarchitecture.unicontext.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountId;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountNumber;

import java.util.Optional;

public interface BankAccountRepositoryPort {
    Optional<BankAccount> find(AccountNumber accountNumber);

    BankAccount findRequired(AccountNumber accountNumber);

    void add(BankAccount bankAccount);

    void update(BankAccount bankAccount);
}
