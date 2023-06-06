package io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.core.domain.exception.ValidationMessages;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard.Guard;

import java.math.BigDecimal;

import static io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard.Guard.guard;

public class Balance {

    private final Money value;

    private Balance(Money value) {
        Guard.guard(value).againstNegative(ValidationMessages.BALANCE_NEGATIVE);
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