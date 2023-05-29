package io.albrains.cleanarchitecture.unicontext.model.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public abstract class BaseEntity<ID> {

    private ID id;
}
