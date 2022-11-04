package controller.factory.terminal;

import command.Command;
import command.ValueCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

/**
 * A factory for {@link ValueCommand}s.
 */
public class ValueFactory extends AbstractTwoStringFactory {
    /**
     * Creates a new ValueFactory with the given view and scanner. Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     *
     * @param view the view which this factory will transmit messages to
     * @param s    the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public ValueFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    /**
     * Creates a {@link ValueCommand} that takes in the name of the image to operate on and saves the resulting image,
     * naming it the second argument.
     *
     * @param s1 the name of the image to operate on
     * @param s2 the name of the resulting image
     * @return a {@link Optional<ValueCommand>} containing a valid command
     */
    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new ValueCommand(s1.get(), s2.get()));
    }
}
