package io.albrains.cleanarchitecture.unicontext.core.application.usecase;

import io.albrains.cleanarchitecture.unicontext.core.application.common.CommandHandler;
import io.albrains.cleanarchitecture.unicontext.core.application.common.UseCase;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.DepositFundsRequest;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.VoidResponse;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.TransactionAmount;
import io.albrains.cleanarchitecture.unicontext.core.application.port.output.BankAccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class DepositFundsUseCase implements CommandHandler<DepositFundsRequest, VoidResponse> {

    private final BankAccountRepositoryPort repository;

    @Override
    @Transactional
    public VoidResponse handle(DepositFundsRequest depositFundsRequest) {
        var accountNumber = getAccountNumber(depositFundsRequest);
        var amount = getTransactionAmount(depositFundsRequest);

        var bankAccount = repository.findRequired(accountNumber);
        bankAccount.deposit(amount);
        repository.update(bankAccount);
        return VoidResponse.EMPTY;
    }

    private AccountNumber getAccountNumber(DepositFundsRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private TransactionAmount getTransactionAmount(DepositFundsRequest request) {
        return TransactionAmount.of(request.getAmount());
    }
}
