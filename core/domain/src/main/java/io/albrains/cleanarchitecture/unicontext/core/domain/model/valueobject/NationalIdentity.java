package io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject;

import io.albrains.cleanarchitecture.unicontext.core.domain.exception.ValidationMessages;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard.Guard;

import static io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard.Guard.guard;

public class NationalIdentity {

    private final String idNumber;

    private NationalIdentity(String idNumber) {
        Guard.guard(idNumber).againstNull(ValidationMessages.BALANCE_NEGATIVE);
        this.idNumber = idNumber;
    }

    public static NationalIdentity of(String idNumber) {
        return new NationalIdentity(idNumber);
    }

    public String getIdNumber() {
        return idNumber;
    }
}
