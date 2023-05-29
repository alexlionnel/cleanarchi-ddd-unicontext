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
public class OpenAccountRequest {
    private String nationalIdentityNumber;
    private String firstName;
    private String lastName;
    private BigDecimal balance;
}