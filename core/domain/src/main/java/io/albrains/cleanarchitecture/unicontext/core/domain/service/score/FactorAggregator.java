package io.albrains.cleanarchitecture.unicontext.core.domain.service.score;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.entity.BankAccount;

public interface FactorAggregator {
    int aggregate(BankAccount bankAccount);
}
