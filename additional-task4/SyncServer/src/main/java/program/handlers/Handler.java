package program.handlers;

import program.params.HandlerParams;

import java.io.IOException;

public interface Handler {
    void exec(HandlerParams handlerParams) throws IOException;
}
