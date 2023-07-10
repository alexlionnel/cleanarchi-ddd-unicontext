package io.albrains.cleanarchitecture.unicontext.core.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DepositFundsRequest {
    private String accountNumber;
    private BigDecimal amount;
}
