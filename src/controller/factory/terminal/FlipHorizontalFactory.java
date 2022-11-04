package controller.factory.terminal;

import command.Command;
import command.FlipHorizontalCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

/**
 * A factory for {@link FlipHorizontalCommand}s.
 */
public class FlipHorizontalFactory extends AbstractTwoStringFactory {
    /**
     * Creates a new FlipHorizontalFactory with the given view and scanner. Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     *
     * @param view the view which this factory will transmit messages to
     * @param s    the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public FlipHorizontalFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    /**
     * Creates a new {@link FlipHorizontalCommand} that flips the image with the name of the first argument and saves
     * the resulting image with the name of the second argument
     *
     * @param s1 the name of the image to flip
     * @param s2 the name of the resulting image to save to the model
     * @return a new {@link Optional<FlipHorizontalCommand>} containing a valid command.
     */
    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new FlipHorizontalCommand(s1.get(), s2.get()));
    }

}
