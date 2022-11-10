package controller.factory.terminal;

import command.Command;
import command.SharpenCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

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

    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new SharpenCommand(s1.get(), s2.get()));
    }
}
