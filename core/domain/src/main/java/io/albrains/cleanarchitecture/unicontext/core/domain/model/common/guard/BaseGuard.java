package io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard;

import io.albrains.cleanarchitecture.unicontext.core.domain.exception.ValidationException;

import java.util.function.BooleanSupplier;

public class BaseGuard<T> {
    protected final T value;

    public BaseGuard(T value) {
        this.value = value;
    }

    protected void against(BooleanSupplier tester, String message) {
        if (tester.getAsBoolean()) {
            throw new ValidationException(message);
        }
    }

    public void againstNull(String message) {
        if (value == null) {
            throw new ValidationException(message);
        }
    }
}
