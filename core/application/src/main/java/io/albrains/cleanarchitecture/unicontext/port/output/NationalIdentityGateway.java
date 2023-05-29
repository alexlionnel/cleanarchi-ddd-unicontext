package io.albrains.cleanarchitecture.unicontext.port.output;

import io.albrains.cleanarchitecture.unicontext.model.valueobject.NationalIdentity;

public interface NationalIdentityGateway {
    boolean exists(NationalIdentity nationalIdentity);
}
