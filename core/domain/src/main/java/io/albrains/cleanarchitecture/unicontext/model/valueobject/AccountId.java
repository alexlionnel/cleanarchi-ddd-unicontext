package io.albrains.cleanarchitecture.unicontext.model.valueobject;

import java.util.UUID;

public class AccountId extends BaseId<UUID> {
    private AccountId(UUID value) {
        super(value);
    }

    public static AccountId of(UUID uuid) {
        return new AccountId(uuid);
    }

    public static AccountId next() {
        return new AccountId(UUID.randomUUID());
    }
}