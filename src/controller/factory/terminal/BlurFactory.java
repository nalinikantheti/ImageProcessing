package controller.factory.terminal;

import command.BlurCommand;
import command.Command;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class BlurFactory extends AbstractTwoStringFactory {

    /**
     * Creates a new BlurFactory with the given view and scanner.
     * Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     *
     * @param view the view which this factory will transmit messages to
     * @param s    the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public BlurFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new BlurCommand(s1.get(), s2.get()));
    }
}
