package io.albrains.cleanarchitecture.unicontext.core.application.usecase;

import io.albrains.cleanarchitecture.unicontext.core.application.common.CommandHandler;
import io.albrains.cleanarchitecture.unicontext.core.application.common.UseCase;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.ViewAccountRequest;
import io.albrains.cleanarchitecture.unicontext.core.application.dto.ViewAccountResponse;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.core.application.port.output.BankAccountRepositoryPort;
import io.albrains.cleanarchitecture.unicontext.core.domain.service.score.ScoreCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class ViewAccountUseCase implements CommandHandler<ViewAccountRequest, ViewAccountResponse> {

    private final BankAccountRepositoryPort repository;
    private final ScoreCalculator scoreCalculator;

    @Override
    @Transactional(readOnly = true)
    public ViewAccountResponse handle(ViewAccountRequest viewAccountRequest) {
        var accountNumber = getAccountNumber(viewAccountRequest);
        var bankAccount = repository.findRequired(accountNumber);
        return getResponse(bankAccount);
    }

    private AccountNumber getAccountNumber(ViewAccountRequest request) {
        return AccountNumber.of(request.getAccountNumber());
    }

    private ViewAccountResponse getResponse(BankAccount bankAccount) {
        var accountNumber = bankAccount.getAccountNumber().toString();
        var fullName = bankAccount.getAccountHolderName().toString();
        var balance = bankAccount.getBalance();
        var score = scoreCalculator.calculate(bankAccount);

        return ViewAccountResponse.builder()
                .accountNumber(accountNumber)
                .fullName(fullName)
                .balance(balance.getValue().getValue())
                .score(score)
                .build();
    }
}
