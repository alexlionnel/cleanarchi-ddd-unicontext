package io.albrains.cleanarchitecture.unicontext.model.common.guard;

import io.albrains.cleanarchitecture.unicontext.model.valueobject.Money;

public class MoneyGuard extends BaseGuard<Money> {

    public MoneyGuard(Money value) {
        super(value);
    }

    public void againstNegative(String message) {
        against(value::isNegative, message);
    }

    public void againstNonPositive(String message) {
        against(value::isNonPositive, message);
    }
}
