package io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.core.domain.exception.ValidationMessages;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard.Guard;

import java.math.BigDecimal;

import static io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard.Guard.guard;

public class Balance {

    private final Money money;

    private Balance(Money money) {
        Guard.guard(money).againstNegative(ValidationMessages.BALANCE_NEGATIVE);
        this.money = money;
    }

    public Balance add(TransactionAmount amount) {
        return of(money.add(amount.getMoney()));
    }

    public Balance subtract(TransactionAmount amount) {
        return of(money.subtract(amount.getMoney()));
    }

    public static Balance of(Money value) {
        return new Balance(value);
    }

    public static Balance of(BigDecimal value) {
        return new Balance(Money.of(value));
    }

    public Money getMoney() {
        return money;
    }
}