package io.albrains.cleanarchitecture.unicontext.core.domain.service.bankaccount;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.TransactionAmount;

public class TransfertFundsService {

    public void transfertFunds(BankAccount bankAccountSource, BankAccount bankAccountTarget, TransactionAmount amount) {
        bankAccountSource.withdraw(amount);
        bankAccountTarget.deposit(amount);
    }
}
