package io.albrains.cleanarchitecture.unicontext.core.domain.service.bankaccount;

import io.albrains.cleanarchitecture.unicontext.core.domain.exception.ValidationException;
import io.albrains.cleanarchitecture.unicontext.core.domain.exception.ValidationMessages;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.entity.BankAccount;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountHolderName;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountId;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.AccountNumber;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.Balance;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.NationalIdentity;
import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.TransactionAmount;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class TransfertFundsServiceTest {

    @InjectMocks
    private TransfertFundsService transfertFundsService;

    @Test
    void shouldTransfertFunds() {
        BankAccount bankAccountSource = BankAccount.builder()
                .accountId(AccountId.next())
                .nationalIdentity(NationalIdentity.of(RandomStringUtils.random(6)))
                .accountNumber(AccountNumber.next())
                .accountHolderName(AccountHolderName.of(
                        RandomStringUtils.random(6, true, false),
                        RandomStringUtils.random(6, true, false)
                ))
                .openingDate(LocalDate.now())
                .balance(Balance.of(BigDecimal.valueOf(100)))
                .build();

        BankAccount bankAccountTarget = BankAccount.builder()
                .accountId(AccountId.next())
                .nationalIdentity(NationalIdentity.of(RandomStringUtils.random(6)))
                .accountNumber(AccountNumber.next())
                .accountHolderName(AccountHolderName.of(
                        RandomStringUtils.random(6, true, false),
                        RandomStringUtils.random(6, true, false)
                ))
                .openingDate(LocalDate.now())
                .balance(Balance.of(BigDecimal.valueOf(75)))
                .build();

        transfertFundsService.transfertFunds(bankAccountSource, bankAccountTarget, TransactionAmount.of(BigDecimal.valueOf(50)));

        assertThat(bankAccountSource.getBalance().getMoney().getAmount()).isEqualTo(BigDecimal.valueOf(50));
        assertThat(bankAccountTarget.getBalance().getMoney().getAmount()).isEqualTo(BigDecimal.valueOf(125));
    }

    @Test
    void shouldNotTransfertFundsWhenBankAccountSourceHasInsufficientFunds() {
        BankAccount bankAccountSource = BankAccount.builder()
                .accountId(AccountId.next())
                .nationalIdentity(NationalIdentity.of(RandomStringUtils.random(6)))
                .accountNumber(AccountNumber.next())
                .accountHolderName(AccountHolderName.of(
                        RandomStringUtils.random(6, true, false),
                        RandomStringUtils.random(6, true, false)
                ))
                .openingDate(LocalDate.now())
                .balance(Balance.of(BigDecimal.valueOf(100)))
                .build();

        BankAccount bankAccountTarget = BankAccount.builder()
                .accountId(AccountId.next())
                .nationalIdentity(NationalIdentity.of(RandomStringUtils.random(6)))
                .accountNumber(AccountNumber.next())
                .accountHolderName(AccountHolderName.of(
                        RandomStringUtils.random(6, true, false),
                        RandomStringUtils.random(6, true, false)
                ))
                .openingDate(LocalDate.now())
                .balance(Balance.of(BigDecimal.valueOf(75)))
                .build();

        assertThatThrownBy(() -> transfertFundsService.transfertFunds(bankAccountSource, bankAccountTarget, TransactionAmount.of(BigDecimal.valueOf(200))))
                .isInstanceOf(ValidationException.class)
                .hasMessage(ValidationMessages.INSUFFICIENT_FUNDS);
    }
}
