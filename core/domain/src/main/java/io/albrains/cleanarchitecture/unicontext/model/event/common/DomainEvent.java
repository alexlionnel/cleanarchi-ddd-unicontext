package io.albrains.cleanarchitecture.unicontext.model.event.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DomainEvent {
    private LocalDateTime timestamp;
}
