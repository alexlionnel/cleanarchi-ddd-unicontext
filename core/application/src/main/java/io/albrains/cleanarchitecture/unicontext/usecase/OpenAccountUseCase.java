package io.albrains.cleanarchitecture.unicontext.usecase;

import io.albrains.cleanarchitecture.unicontext.common.CommandHandler;
import io.albrains.cleanarchitecture.unicontext.common.UseCase;
import io.albrains.cleanarchitecture.unicontext.dto.OpenAccountRequest;
import io.albrains.cleanarchitecture.unicontext.dto.OpenAccountResponse;
import io.albrains.cleanarchitecture.unicontext.exception.ValidationException;
import io.albrains.cleanarchitecture.unicontext.exception.ValidationMessages;
import io.albrains.cleanarchitecture.unicontext.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.model.event.AccountOpened;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountHolderName;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountId;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.Balance;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.NationalIdentity;
import io.albrains.cleanarchitecture.unicontext.port.output.AccountOpenedMessagePublisher;
import io.albrains.cleanarchitecture.unicontext.port.output.BankAccountRepositoryPort;
import io.albrains.cleanarchitecture.unicontext.port.output.CustomerGateway;
import io.albrains.cleanarchitecture.unicontext.port.output.NationalIdentityGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@UseCase
public class OpenAccountUseCase implements CommandHandler<OpenAccountRequest, OpenAccountResponse> {

    private final NationalIdentityGateway nationalIdentityGateway;
    private final CustomerGateway customerGateway;
    private final BankAccountRepositoryPort bankAccountRepositoryPort;
    private final AccountOpenedMessagePublisher accountOpenedMessagePublisher;

    @Override
    @Transactional
    public OpenAccountResponse handle(OpenAccountRequest openAccountRequest) {
        var nationalIdentity = getNationalIdentity(openAccountRequest);
        var accountHolderName = getAccountHolderName(openAccountRequest);
        var balance = getBalance(openAccountRequest);

        var timestamp = LocalDateTime.now();

        var isBlacklisted = customerGateway.isBlacklisted(nationalIdentity);

        if(isBlacklisted) {
            throw new ValidationException(ValidationMessages.NATIONAL_IDENTITY_NUMBER_BLACKLISTED);
        }

        var bankAccount = createBankAccount(nationalIdentity, accountHolderName, balance, timestamp);

        // TODO: VC: Value object for national identity number
        var exists = nationalIdentityGateway.exists(nationalIdentity);
        if(!exists) {
            throw new ValidationException(ValidationMessages.NATIONAL_IDENTITY_NUMBER_NONEXISTENT);
        }

        bankAccountRepositoryPort.add(bankAccount);

        var accountOpened = getAccountOpened(bankAccount, timestamp);
        accountOpenedMessagePublisher.publishEvent(accountOpened);

        return getResponse(bankAccount);
    }

    private NationalIdentity getNationalIdentity(OpenAccountRequest request) {
        return NationalIdentity.of(request.getNationalIdentityNumber());
    }

    private AccountHolderName getAccountHolderName(OpenAccountRequest request) {
        return AccountHolderName.of(request.getFirstName(), request.getLastName());
    }

    private Balance getBalance(OpenAccountRequest request) {
        return Balance.of(request.getBalance());
    }

    private BankAccount createBankAccount(NationalIdentity nationalIdentity, AccountHolderName accountHolderName, Balance balance, LocalDateTime timestamp) {
        var accountId = AccountId.next();
        var accountNumber = AccountNumber.next();
        var openingDate = timestamp.toLocalDate();
        return new BankAccount(accountId, accountNumber, nationalIdentity, accountHolderName, openingDate, balance);
    }

    private AccountOpened getAccountOpened(BankAccount bankAccount, LocalDateTime timestamp) {
        return new AccountOpened(timestamp, bankAccount.getId(), bankAccount.getAccountHolderName(), bankAccount.getBalance());
    }

    private OpenAccountResponse getResponse(BankAccount bankAccount) {
        var accountNumber = bankAccount.getAccountNumber().toString();

        return OpenAccountResponse.builder()
                .accountNumber(accountNumber)
                .build();
    }
}
