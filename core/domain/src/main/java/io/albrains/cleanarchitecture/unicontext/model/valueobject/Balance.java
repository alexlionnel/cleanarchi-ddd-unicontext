package io.albrains.cleanarchitecture.unicontext.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.exception.ValidationMessages;

import java.math.BigDecimal;

import static io.albrains.cleanarchitecture.unicontext.model.common.guard.Guard.guard;

public record Balance(Money value) {

    public Balance {
        guard(value).againstNegative(ValidationMessages.BALANCE_NEGATIVE);
    }

    public Balance add(TransactionAmount amount) {
        return of(value.add(amount.value()));
    }

    public Balance subtract(TransactionAmount amount) {
        return of(value.subtract(amount.value()));
    }

    public static Balance of(Money value) {
        return new Balance(value);
    }

    public static Balance of(BigDecimal value) {
        return new Balance(Money.of(value));
    }
}