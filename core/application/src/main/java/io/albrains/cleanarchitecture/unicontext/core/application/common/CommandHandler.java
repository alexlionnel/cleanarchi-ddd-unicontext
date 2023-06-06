package io.albrains.cleanarchitecture.unicontext.core.application.common;

public interface CommandHandler<Request, Response> {

    Response handle(Request request);
}
