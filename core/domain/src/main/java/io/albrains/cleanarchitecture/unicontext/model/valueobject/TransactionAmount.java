package io.albrains.cleanarchitecture.unicontext.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.exception.ValidationMessages;

import java.math.BigDecimal;

import static io.albrains.cleanarchitecture.unicontext.model.common.guard.Guard.guard;

public record TransactionAmount(Money value) {

    public TransactionAmount {
        guard(value).againstNonPositive(ValidationMessages.AMOUNT_NOT_POSITIVE);
    }

    public boolean greaterThan(Balance balance) {
        return value.greaterThan(balance.value());
    }

    public static TransactionAmount of(Money value) {
        return new TransactionAmount(value);
    }

    public static TransactionAmount of(BigDecimal value) {
        return of(Money.of(value));
    }
}