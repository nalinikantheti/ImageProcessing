package controller.factory.terminal;

import command.Command;
import command.SaveCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

/**
 * A factory for creating {@link SaveCommand}s.
 */
public class SaveFactory extends AbstractTwoStringFactory{
    /**
     * Creates a new SaveFactory with the given view and scanner. Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     * @param view the view which this factory will transmit messages to
     * @param s the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public SaveFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    /**
     * Creates a new {@link SaveCommand} that takes in a filepath and an image name and saves the image to that filepath.
     * @param s1 the filepath
     * @param s2 the name of the image to save to the filesystem
     * @return a {@link Optional<SaveCommand>} containing a valid command
     */
    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new SaveCommand(s1.get(), s2.get()));
    }
}
