package io.albrains.cleanarchitecture.unicontext.core.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ViewAccountRequest {
    private String accountNumber;
}
