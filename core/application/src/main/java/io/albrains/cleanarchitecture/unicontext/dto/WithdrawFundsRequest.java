package io.albrains.cleanarchitecture.unicontext.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class WithdrawFundsRequest {
    private String accountNumber;
    private BigDecimal amount;
}
