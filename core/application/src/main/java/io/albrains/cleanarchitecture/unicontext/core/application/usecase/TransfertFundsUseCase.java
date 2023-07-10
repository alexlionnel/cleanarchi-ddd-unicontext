package io.albrains.cleanarchitecture.unicontext.core.application.usecase;

import io.albrains.cleanarchitecture.unicontext.core.application.common.CommandHandler;
import io.albrains.cleanarchitecture.unicontext.core.application.common.UseCase;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.TransfertFundsRequest;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.VoidResponse;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.TransactionAmount;
import io.albrains.cleanarchitecture.unicontext.core.application.port.output.BankAccountRepositoryPort;
import io.albrains.cleanarchitecture.unicontext.core.domain.service.bankaccount.TransfertFundsService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class TransfertFundsUseCase implements CommandHandler<TransfertFundsRequest, VoidResponse> {

    private final BankAccountRepositoryPort bankAccountRepositoryPort;
    private final TransfertFundsService transfertFundsService;

    @Override
    @Transactional
    public VoidResponse handle(TransfertFundsRequest transfertFundsRequest) {
        var accountNumberSource = getAccountNumberSource(transfertFundsRequest);
        var accountNumberTarget = getAccountNumberTarget(transfertFundsRequest);
        var amount = getTransactionAmount(transfertFundsRequest);

        var bankAccountSource = bankAccountRepositoryPort.findRequired(accountNumberSource);
        var bankAccountTarget = bankAccountRepositoryPort.findRequired(accountNumberTarget);

        transfertFundsService.transfertFunds(bankAccountSource, bankAccountTarget, amount);
        bankAccountRepositoryPort.update(bankAccountSource);
        bankAccountRepositoryPort.update(bankAccountTarget);

        return VoidResponse.EMPTY;
    }

    private AccountNumber getAccountNumberSource(TransfertFundsRequest request) {
        return AccountNumber.of(request.getAccountNumberSource());
    }

    private AccountNumber getAccountNumberTarget(TransfertFundsRequest request) {
        return AccountNumber.of(request.getAccountNumberTarget());
    }

    private TransactionAmount getTransactionAmount(TransfertFundsRequest request) {
        return TransactionAmount.of(request.getAmount());
    }
}
