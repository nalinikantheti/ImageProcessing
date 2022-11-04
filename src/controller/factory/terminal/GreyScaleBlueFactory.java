package controller.factory.terminal;

import command.Command;
import command.GreyScaleBlueCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

/**
 * A factory for creating {@link GreyScaleBlueCommand}s.
 */
public class GreyScaleBlueFactory extends AbstractTwoStringFactory {
    /**
     * Creates a new GreyScaleBlueFactory with the given view and scanner. Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     *
     * @param view the view which this factory will transmit messages to
     * @param s    the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public GreyScaleBlueFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    /**
     * Creates a new {@link GreyScaleBlueCommand} that takes in the image with the name of the first string argument
     * and saves the resulting image with the name of the second string argument.
     *
     * @param s1 the name of the image to perform the greyscale operation on
     * @param s2 the name of the resulting image to save to the model
     * @return a {@link Optional<GreyScaleBlueCommand>} containing a valid command
     */
    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new GreyScaleBlueCommand(s1.get(), s2.get()));
    }
}
