package controller.factory.terminal;

import command.BlurCommand;
import command.Command;
import command.SepiaCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

/**
 * A factory for creating {@link SepiaCommand}.
 */
public class SepiaFactory extends AbstractTwoStringFactory {
    /**
     * Creates a new SepiaFactory with the given view and scanner.
     * Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     *
     * @param view the view which this factory will transmit messages to
     * @param s    the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public SepiaFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    /**
     * Creates a new {@link SepiaCommand} that uses the image with the name
     * of the first argument and saves the resulting image to the name of the
     * second argument.
     * @param s1 the name of the image to perform the sepia operation
     * @param s2 the name of the sepia image
     * @return a new Optional of a new Sepia Command containing a valid command
     *
     */
    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new SepiaCommand(s1.get(), s2.get()));
    }
}
