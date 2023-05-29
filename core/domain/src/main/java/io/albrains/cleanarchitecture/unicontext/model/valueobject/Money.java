package io.albrains.cleanarchitecture.unicontext.model.valueobject;

import java.math.BigDecimal;

public record Money(BigDecimal value) {

    public static final Money ZERO = Money.of(BigDecimal.ZERO);

    public Money add(Money other) {
        return Money.of(value.add(other.value()));
    }

    public Money subtract(Money other) {
        return Money.of(value.subtract(other.value()));
    }

    public boolean greaterThan(Money other) {
        return value.compareTo(other.value) > 0;
    }

    public boolean lessThan(Money other) {
        return value.compareTo(other.value) < 0;
    }

    public boolean lessThanOrEqualTo(Money other) {
        return value.compareTo(other.value) <= 0;
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
}