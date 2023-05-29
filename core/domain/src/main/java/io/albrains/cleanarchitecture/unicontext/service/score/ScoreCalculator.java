package io.albrains.cleanarchitecture.unicontext.service.score;

import io.albrains.cleanarchitecture.unicontext.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.Score;

public interface ScoreCalculator {
    Score calculate(BankAccount bankAccount);
}
