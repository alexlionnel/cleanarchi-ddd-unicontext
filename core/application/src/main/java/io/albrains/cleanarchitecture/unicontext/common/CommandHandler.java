package io.albrains.cleanarchitecture.unicontext.common;

public interface CommandHandler<Request, Response> {

    Response handle(Request request);
}
