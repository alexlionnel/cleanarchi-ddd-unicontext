package io.albrains.cleanarchitecture.unicontext.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.exception.ValidationMessages;

import java.math.BigDecimal;

import static io.albrains.cleanarchitecture.unicontext.model.common.guard.Guard.guard;

public class Balance {

    private final Money value;

    private Balance(Money value) {
        guard(value).againstNegative(ValidationMessages.BALANCE_NEGATIVE);
        this.value = value;
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

    public Money getValue() {
        return value;
    }
}