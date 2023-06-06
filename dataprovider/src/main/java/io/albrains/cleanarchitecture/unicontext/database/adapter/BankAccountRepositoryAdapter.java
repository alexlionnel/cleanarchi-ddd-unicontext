package io.albrains.cleanarchitecture.unicontext.database.adapter;

import io.albrains.cleanarchitecture.unicontext.database.mapper.BankAccountMapper;
import io.albrains.cleanarchitecture.unicontext.database.repository.BankAccountJpaRepository;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.core.application.port.output.BankAccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BankAccountRepositoryAdapter implements BankAccountRepositoryPort {

    private final BankAccountJpaRepository bankAccountJpaRepository;
    private final BankAccountMapper bankAccountMapper;

    @Override
    public Optional<BankAccount> find(AccountNumber accountNumber) {
        return Optional.empty();
    }

    @Override
    public BankAccount findRequired(AccountNumber accountNumber) {
        return null;
    }

    @Override
    public void add(BankAccount bankAccount) {

    }

    @Override
    public void update(BankAccount bankAccount) {

    }
}
