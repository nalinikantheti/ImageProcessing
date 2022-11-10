package controller.factory.terminal;

import command.Command;
import command.SepiaCommand;
import command.SharpenCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

/**
 * A factory for creating a new {@link SharpenCommand}.
 */
public class SharpenFactory extends AbstractTwoStringFactory {

    /**
     * Creates a new SharpenFactory with the given view and scanner.
     * Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     *
     * @param view the view which this factory will transmit messages to
     * @param s    the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public SharpenFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    /**
     * Creates a new {@link SharpenCommand} that uses the image with the name
     * of the first argument and saves the resulting image to the name of the
     * second argument.
     * @param s1 the name of the image to perform the sharpen operation
     * @param s2 the name of the sharpened image
     * @return a new Optional of a new Sharpen Command containing a valid command
     *
     */
    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new SharpenCommand(s1.get(), s2.get()));
    }
}
