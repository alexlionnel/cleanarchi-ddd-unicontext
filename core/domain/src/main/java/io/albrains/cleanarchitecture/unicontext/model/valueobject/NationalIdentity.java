package io.albrains.cleanarchitecture.unicontext.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.exception.ValidationMessages;

import static io.albrains.cleanarchitecture.unicontext.model.common.guard.Guard.guard;

public record NationalIdentity(String number) {

    public NationalIdentity {
        guard(number).againstNull(ValidationMessages.BALANCE_NEGATIVE);
    }

    public static NationalIdentity of(String number) {
        return new NationalIdentity(number);
    }
}
