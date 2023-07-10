package io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.core.domain.exception.ValidationMessages;

import java.math.BigDecimal;

import static io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard.Guard.guard;

public class TransactionAmount {

    private final Money money;

    private TransactionAmount(Money money) {
        guard(money).againstNonPositive(ValidationMessages.AMOUNT_NOT_POSITIVE);
        this.money = money;
    }

    public boolean greaterThan(Balance balance) {
        return money.greaterThan(balance.getMoney());
    }

    public static TransactionAmount of(Money value) {
        return new TransactionAmount(value);
    }

    public static TransactionAmount of(BigDecimal value) {
        return of(Money.of(value));
    }

    public Money getMoney() {
        return money;
    }
}