package io.albrains.cleanarchitecture.unicontext.dto;

import lombok.Data;

@Data
public class VoidResponse {
    public static final VoidResponse EMPTY = new VoidResponse();
}
