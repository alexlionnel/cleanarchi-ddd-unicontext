package io.albrains.cleanarchitecture.unicontext.core.domain.model.event;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountHolderName;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.event.common.DomainEvent;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountId;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.Balance;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AccountOpened extends DomainEvent {
    private final AccountId accountId;
    private final AccountNumber accountNumber;
    private final AccountHolderName accountHolderName;
    private final Balance balance;

    public AccountOpened(LocalDateTime timestamp, AccountId accountId, AccountNumber accountNumber, AccountHolderName accountHolderName, Balance balance) {
        super(timestamp);
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
}
