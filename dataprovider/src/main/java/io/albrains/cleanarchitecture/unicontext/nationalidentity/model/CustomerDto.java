package io.albrains.cleanarchitecture.unicontext.nationalidentity.model;

import lombok.Data;

@Data
public class CustomerDto {
    private String id;
    private boolean blacklisted;
}