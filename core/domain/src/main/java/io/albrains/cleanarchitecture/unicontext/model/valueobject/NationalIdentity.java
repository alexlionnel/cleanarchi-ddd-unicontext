package io.albrains.cleanarchitecture.unicontext.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.exception.ValidationMessages;

import static io.albrains.cleanarchitecture.unicontext.model.common.guard.Guard.guard;

public class NationalIdentity {

    private final String number;

    private NationalIdentity(String number) {
        guard(number).againstNull(ValidationMessages.BALANCE_NEGATIVE);
        this.number = number;
    }

    public static NationalIdentity of(String number) {
        return new NationalIdentity(number);
    }

    public String getNumber() {
        return number;
    }
}
