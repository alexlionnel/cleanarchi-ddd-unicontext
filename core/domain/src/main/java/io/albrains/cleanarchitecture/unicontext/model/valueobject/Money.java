package io.albrains.cleanarchitecture.unicontext.model.valueobject;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal value;

    private Money(BigDecimal value) {
        this.value = value;
    }

    public static final Money ZERO = Money.of(BigDecimal.ZERO);

    public Money add(Money other) {
        return Money.of(value.add(other.getValue()));
    }

    public Money subtract(Money other) {
        return Money.of(value.subtract(other.getValue()));
    }

    public boolean greaterThan(Money other) {
        return value.compareTo(other.getValue()) > 0;
    }

    public boolean lessThan(Money other) {
        return value.compareTo(other.getValue()) < 0;
    }

    public boolean lessThanOrEqualTo(Money other) {
        return value.compareTo(other.getValue()) <= 0;
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

    public BigDecimal getValue() {
        return value;
    }
}