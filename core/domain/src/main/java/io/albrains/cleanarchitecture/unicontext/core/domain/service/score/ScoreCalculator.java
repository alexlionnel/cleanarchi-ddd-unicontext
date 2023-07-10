package io.albrains.cleanarchitecture.unicontext.core.domain.service.score;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.Score;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.entity.BankAccount;

public interface ScoreCalculator {
    Score calculate(BankAccount bankAccount);
}
