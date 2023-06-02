package io.albrains.cleanarchitecture.unicontext.model.entity;

import io.albrains.cleanarchitecture.unicontext.exception.ValidationException;
import io.albrains.cleanarchitecture.unicontext.exception.ValidationMessages;
import io.albrains.cleanarchitecture.unicontext.model.common.AggregateRoot;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountHolderName;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountId;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.Balance;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.Money;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.NationalIdentity;
import io.albrains.cleanarchitecture.unicontext.model.valueobject.TransactionAmount;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

import static io.albrains.cleanarchitecture.unicontext.model.common.guard.Guard.guard;

public class BankAccount extends AggregateRoot<AccountId> {
    private final AccountNumber accountNumber;
    private final NationalIdentity nationalIdentity;
    private final AccountHolderName accountHolderName;
    private final LocalDate openingDate;
    private Balance balance;

    public BankAccount(AccountId accountId, AccountNumber accountNumber,
                       NationalIdentity nationalIdentity, AccountHolderName accountHolderName,
                       LocalDate openingDate, Balance balance) {
        super.setId(accountId);

        guard(nationalIdentity).againstNull(ValidationMessages.NATIONAL_IDENTITY_NUMBER_EMPTY);
        guard(accountId).againstNull(ValidationMessages.ACCOUNT_ID_EMPTY);
        guard(accountNumber).againstNull(ValidationMessages.ACCOUNT_NUMBER_EMPTY);
        guard(accountHolderName).againstNull(ValidationMessages.ACCOUNT_HOLDER_NAME_EMPTY);
        guard(openingDate).againstNull(ValidationMessages.OPENING_DATE_EMPTY);
        guard(balance).againstNull(ValidationMessages.BALANCE_EMPTY);


        this.accountNumber = accountNumber;
        this.nationalIdentity = nationalIdentity;
        this.accountHolderName = accountHolderName;
        this.openingDate = openingDate;
        this.balance = balance;
    }

    public BankAccount(BankAccount other) {
        this(other.getId(), other.getAccountNumber(), other.getNationalIdentity(), other.getAccountHolderName(), other.getOpeningDate(), other.getBalance());
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public NationalIdentity getNationalIdentity() {
        return nationalIdentity;
    }

    public AccountHolderName getAccountHolderName() {
        return accountHolderName;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public Balance getBalance() {
        return balance;
    }

    public void deposit(TransactionAmount amount) {
        balance = balance.add(amount);
    }

    public void withdraw(TransactionAmount amount) {
        if (amount.greaterThan(balance)) {
            throw new ValidationException(ValidationMessages.INSUFFICIENT_FUNDS);
        }

        balance = balance.subtract(amount);
    }
}
