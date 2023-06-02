package io.albrains.cleanarchitecture.unicontext.usecase;

import io.albrains.cleanarchitecture.unicontext.common.CommandHandler;
import io.albrains.cleanarchitecture.unicontext.common.UseCase;
import io.albrains.cleanarchitecture.unicontext.dto.ViewAccountRequest;
import io.albrains.cleanarchitecture.unicontext.dto.ViewAccountResponse;
import io.albrains.cleanarchitecture.unicontext.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.port.output.BankAccountRepositoryPort;
import io.albrains.cleanarchitecture.unicontext.service.score.ScoreCalculator;
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
