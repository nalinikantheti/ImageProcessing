package mock;

import command.Command;
import controller.factory.terminal.CommandFactory;

import java.util.Optional;

/**
 * A Mock Command Factory that is used for testing the controller.
 */
public class MockFactory implements CommandFactory {
    private StringBuilder log;

    /**
     * constructor for a Mock Factory using a StringBuilder to log
     * when this mockFactory is called.
     *
     * @param log the StringBuilder this mockFactory appends to.
     */
    public MockFactory(StringBuilder log) {
        this.log = log;
    }

    /**
     * makes a new MockCommand and passes in this log.
     *
     * @return a MockCommand or an Empty Optional Object.
     */
    @Override
    public Optional<Command> make() {
        return Optional.of(new MockCommand(log));
    }
}
