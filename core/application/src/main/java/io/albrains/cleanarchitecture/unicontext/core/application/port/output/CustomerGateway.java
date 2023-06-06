package io.albrains.cleanarchitecture.unicontext.core.application.port.output;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.NationalIdentity;

public interface CustomerGateway {
    boolean isBlacklisted(NationalIdentity nationalIdentity);
}
