package io.albrains.cleanarchitecture.unicontext.service.score;

import io.albrains.cleanarchitecture.unicontext.model.entity.BankAccount;

public interface FactorAggregator {
    int aggregate(BankAccount bankAccount);
}
