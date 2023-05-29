package io.albrains.cleanarchitecture.unicontext.model.event;

import io.albrains.cleanarchitecture.unicontext.model.event.common.DomainEvent;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountHolderName;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountId;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.Balance;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AccountOpened extends DomainEvent {
    private final AccountId accountId;
    private final AccountHolderName accountHolderName;
    private final Balance balance;

    public AccountOpened(LocalDateTime timestamp, AccountId accountId, AccountHolderName accountHolderName, Balance balance) {
        super(timestamp);
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }
}
