package controller.factory.terminal;

import command.BrightenCommand;
import command.Command;
import command.DarkenCommand;
import util.ImageProcessorUtils;
import view.ImageProcessorView;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static util.ImageProcessorUtils.ensureNotNull;

/**
 * A factory that creates {@link BrightenCommand}s.
 */
public class BrightenFactory extends AbstractShadeChangeFactory {
    /**
     * Creates a new BrightenFactory with the given view and scanner. Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     * @param view the view which this factory will transmit messages to
     * @param s the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public BrightenFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    /**
     * Returns a new {@link BrightenCommand} that uses the image with the given name, brightens it by the given intensity,
     * and saves the resulting image to the model with the given name.
     * @param s1 the name of the image to brighten
     * @param intensity the amount to brighten the image by
     * @param s2 the name of the resulting image that is saved to the model
     * @return a new {@link Optional<BrightenCommand>} containing a valid command.
     */
    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<Integer> intensity, Optional<String> s2) {
        return Optional.of(new BrightenCommand(s1.get(), intensity.get(), s2.get()));
    }


}
