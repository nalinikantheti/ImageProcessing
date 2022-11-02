package mock;

import command.Command;
import controller.factory.terminal.CommandFactory;

import java.util.Optional;

public class MockFactory implements CommandFactory {
    StringBuilder log;

    public MockFactory(StringBuilder log){
        this.log = log;
    }
    @Override
    public Optional<Command> make() {
        return Optional.of(new MockCommand(log));
    }
}
