package io.albrains.cleanarchitecture.unicontext.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.exception.ValidationMessages;
import io.albrains.cleanarchitecture.unicontext.model.common.guard.Guard;

import java.util.UUID;

public record AccountNumber(UUID value) {
    public AccountNumber {
        Guard.guard(value).againstNull(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
    }

    public static AccountNumber of(UUID value) {
        return new AccountNumber(value);
    }

    public static AccountNumber of(String value) {
        // todo : vérifier que le string est un uuid
        Guard.guard(value).againstNullOrWhitespace(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
        return new AccountNumber(UUID.fromString(value));
    }

    public static AccountNumber next() {
        return new AccountNumber(UUID.randomUUID());
    }
}