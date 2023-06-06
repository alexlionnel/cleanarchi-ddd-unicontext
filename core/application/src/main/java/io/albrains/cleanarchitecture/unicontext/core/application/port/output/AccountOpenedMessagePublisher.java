package io.albrains.cleanarchitecture.unicontext.core.application.port.output;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.event.AccountOpened;

public interface AccountOpenedMessagePublisher {
    void publishEvent(AccountOpened accountOpened);
}
