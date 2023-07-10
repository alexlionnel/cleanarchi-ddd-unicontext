package io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static final Money ZERO = Money.of(BigDecimal.ZERO);

    public Money add(Money other) {
        return Money.of(amount.add(other.getAmount()));
    }

    public Money subtract(Money other) {
        return Money.of(amount.subtract(other.getAmount()));
    }

    public boolean greaterThan(Money other) {
        return amount.compareTo(other.getAmount()) > 0;
    }

    public boolean lessThan(Money other) {
        return amount.compareTo(other.getAmount()) < 0;
    }

    public boolean lessThanOrEqualTo(Money other) {
        return amount.compareTo(other.getAmount()) <= 0;
    }

    public boolean isNegative() {
        return lessThan(ZERO);
    }

    public boolean isNonPositive() {
        return lessThanOrEqualTo(ZERO);
    }

    public static Money of(BigDecimal value) {
        return new Money(value);
    }

    public BigDecimal getAmount() {
        return amount;
    }
}