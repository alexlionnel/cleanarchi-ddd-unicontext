package io.albrains.cleanarchitecture.unicontext.core.domain.model.common.guard;

public class TextGuard extends BaseGuard<String> {

    public TextGuard(String value) {
        super(value);
    }

    public void againstNullOrWhitespace(String message) {
        against(value::isEmpty, message);
    }
}
