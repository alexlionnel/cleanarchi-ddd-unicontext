package io.albrains.cleanarchitecture.unicontext.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.exception.ValidationMessages;

import java.math.BigDecimal;

import static io.albrains.cleanarchitecture.unicontext.model.common.guard.Guard.guard;

public class TransactionAmount {

    private final Money value;

    private TransactionAmount(Money value) {
        guard(value).againstNonPositive(ValidationMessages.AMOUNT_NOT_POSITIVE);
        this.value = value;
    }

    public boolean greaterThan(Balance balance) {
        return value.greaterThan(balance.getValue());
    }

    public static TransactionAmount of(Money value) {
        return new TransactionAmount(value);
    }

    public static TransactionAmount of(BigDecimal value) {
        return of(Money.of(value));
    }
}