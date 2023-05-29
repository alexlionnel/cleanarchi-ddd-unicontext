package io.albrains.cleanarchitecture.unicontext.usecase;

import io.albrains.cleanarchitecture.unicontext.common.CommandHandler;
import io.albrains.cleanarchitecture.unicontext.common.UseCase;
import io.albrains.cleanarchitecture.unicontext.dto.TransfertFundsRequest;
import io.albrains.cleanarchitecture.unicontext.dto.VoidResponse;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.TransactionAmount;
import io.albrains.cleanarchitecture.unicontext.port.output.BankAccountRepositoryPort;
import io.albrains.cleanarchitecture.unicontext.service.bankaccount.TransfertFundsService;
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
