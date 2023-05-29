package io.albrains.cleanarchitecture.unicontext.model.common.guard;

import io.albrains.cleanarchitecture.unicontext.model.valueobject.Money;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Guard {
    public static MoneyGuard guard(Money value) {
        return new MoneyGuard(value);
    }

    public static ObjectGuard guard(Object value) { return new ObjectGuard(value); }

    public static TextGuard guard(String String) {
        return new TextGuard(String);
    }
}
