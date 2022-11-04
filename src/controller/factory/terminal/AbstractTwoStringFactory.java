package controller.factory.terminal;

import command.Command;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

/**
 * A helper factory that converts two string inputs into a command.
 */
public abstract class AbstractTwoStringFactory extends AbstractCommandFactory {
    /**
     * Creates a new AbstractTwoStringFactory with the given view and scanner. Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     *
     * @param view the view which this factory will transmit messages to
     * @param s    the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public AbstractTwoStringFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    /**
     * Waits for two string inputs and converts them into a command. If either parameter is quit keyword or there is no
     * more input, then this method returns an empty {@code Optional} and outputs a corresponding message. Otherwise,
     * returns an {@code optional} containing a command.
     *
     * @return a {@code Optional<Command>}. See above description for details.
     */
    public Optional<Command> make() {
        Optional<String> imageName = waitForString(true);
        Optional<String> newName = waitForString(imageName.isPresent());
        if (imageName.isEmpty() || newName.isEmpty()) {
            return Optional.empty();
        }
        return makeCommand(imageName, newName);

    }

    /**
     * Converts two string arguments into a command. The returned command varies depending on the implementation.
     *
     * @param s1 the first string argument
     * @param s2 the second string argument
     * @return an {@code Optional<Command>} containing a command
     */
    protected abstract Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2);
}
