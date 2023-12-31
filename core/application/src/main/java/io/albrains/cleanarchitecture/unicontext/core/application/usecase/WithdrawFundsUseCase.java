package io.albrains.cleanarchitecture.unicontext.core.application.usecase;

import io.albrains.cleanarchitecture.unicontext.core.application.common.CommandHandler;
import io.albrains.cleanarchitecture.unicontext.core.application.common.UseCase;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.VoidResponse;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.WithdrawFundsRequest;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.TransactionAmount;
import io.albrains.cleanarchitecture.unicontext.core.application.port.output.BankAccountRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class WithdrawFundsUseCase implements CommandHandler<WithdrawFundsRequest, VoidResponse> {

    private final BankAccountRepositoryPort bankAccountRepositoryPort;

    @Override
    @Transactional
    public VoidResponse handle(WithdrawFundsRequest withdrawFundsRequest) {
        var accountNumber = getAccountNumber(withdrawFundsRequest);
        var amount = getTransactionAmount(withdrawFundsRequest);

        var bankAccount = bankAccountRepositoryPort.findRequired(accountNumber);
        bankAccount.withdraw(amount);
        bankAccountRepositoryPort.update(bankAccount);
        return VoidResponse.EMPTY;
    }

    private AccountNumber getAccountNumber(WithdrawFundsRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private TransactionAmount getTransactionAmount(WithdrawFundsRequest request) {
        return TransactionAmount.of(request.getAmount());
    }
}
