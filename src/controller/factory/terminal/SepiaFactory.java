package controller.factory.terminal;

import command.Command;
import command.SepiaCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

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

    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new SepiaCommand(s1.get(), s2.get()));
    }
}
