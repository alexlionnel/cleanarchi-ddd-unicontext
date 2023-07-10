package io.albrains.cleanarchitecture.unicontext.core.application.dto;

import io.albrains.cleanarchitecture.unicontext.core.domain.model.valueobject.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ViewAccountResponse {
    private String accountNumber;
    private String fullName;
    private BigDecimal balance;
    private Score score;
}
