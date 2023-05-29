package io.albrains.cleanarchitecture.unicontext.port.output;

import io.albrains.cleanarchitecture.unicontext.model.event.AccountOpened;

public interface AccountOpenedMessagePublisher {
    void publishEvent(AccountOpened accountOpened);
}
