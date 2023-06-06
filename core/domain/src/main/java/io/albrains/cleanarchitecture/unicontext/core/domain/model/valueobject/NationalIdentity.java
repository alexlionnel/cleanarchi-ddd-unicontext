package io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.core.domain.exception.ValidationMessages;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard.Guard;

import static io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard.Guard.guard;

public class NationalIdentity {

    private final String number;

    private NationalIdentity(String number) {
        Guard.guard(number).againstNull(ValidationMessages.BALANCE_NEGATIVE);
        this.number = number;
    }

    public static NationalIdentity of(String number) {
        return new NationalIdentity(number);
    }

    public String getNumber() {
        return number;
    }
}
