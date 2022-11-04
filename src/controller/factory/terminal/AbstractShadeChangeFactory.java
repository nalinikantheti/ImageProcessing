package controller.factory.terminal;

import command.Command;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

/**
 * A helper factory for commands that change all pixels by some intensity. Handles input conversion for subclasses so
 * that implementors only need to map the arguments to the command constructors.
 */
public abstract class AbstractShadeChangeFactory extends AbstractCommandFactory {
    /**
     * Creates a new AbstractShadeChangeFactory with the given view and scanner. Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     *
     * @param view the view which this factory will transmit messages to
     * @param s    the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public AbstractShadeChangeFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    /**
     * Waits for a string input, an integer input, and a string input and creates a command given with the given parameters.
     * If the user enters "q" or "quit" (case insensitive) for any of these parameters, this method returns an empty
     * {@code Optional}. Otherwise, if the user enters a malformed integer, this method asks the user to re-enter a
     * valid value. If the end of input is reached, a message is sent to the view and an empty {@code Optional} is
     * returned.
     *
     * @return an {@code Optional<Command>}. See the above description for details.
     */
    @Override
    public Optional<Command> make() {
        Optional<String> imageName = waitForString(true);
        Optional<Integer> intensity = waitForInteger(imageName.isPresent(), "Expected integer for intensity");
        Optional<String> newName = waitForString(intensity.isPresent());
        if (imageName.isEmpty() || intensity.isEmpty() || newName.isEmpty()) {
            return Optional.empty();
        }
        return makeCommand(imageName, intensity, newName);
    }

    /**
     * Creates a command following the (string, integer, string) constructor pattern, passing in the respective arguments.
     *
     * @param s1        the first string argument
     * @param intensity the integer argument
     * @param s2        the second string argument
     * @return an {@code Optional<Command>} containing a command. Actual class may vary depending on the implementation.
     */
    protected abstract Optional<Command> makeCommand
    (Optional<String> s1, Optional<Integer> intensity, Optional<String> s2);

}
