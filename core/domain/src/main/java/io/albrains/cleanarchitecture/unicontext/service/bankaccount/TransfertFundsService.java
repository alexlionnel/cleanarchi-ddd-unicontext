package io.albrains.cleanarchitecture.unicontext.service.bankaccount;

import io.albrains.cleanarchitecture.unicontext.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.TransactionAmount;

public class TransfertFundsService {

    public void transfertFunds(BankAccount bankAccountSource, BankAccount bankAccountTarget, TransactionAmount amount) {
        bankAccountSource.withdraw(amount);
        bankAccountTarget.deposit(amount);
    }
}
