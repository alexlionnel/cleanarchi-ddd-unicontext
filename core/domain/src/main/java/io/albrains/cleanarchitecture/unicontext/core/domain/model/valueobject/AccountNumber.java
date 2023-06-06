package io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.core.domain.exception.ValidationMessages;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard.Guard;

import java.util.UUID;

public class AccountNumber {

    private final UUID value;

    private AccountNumber(UUID value) {
        Guard.guard(value).againstNull(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
        this.value = value;
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

    public UUID getValue() {
        return value;
    }
}