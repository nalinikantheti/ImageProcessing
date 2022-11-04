package controller.factory.terminal;

import command.Command;
import command.LumaCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;
/**
 * A factory for creating {@link LumaCommand}s.
 */
public class LumaFactory extends AbstractTwoStringFactory {
    /**
     * Creates a new LumaFactory with the given view and scanner. Throws an {@code IllegalArgumentException}
     * if either parameter is null.
     * @param view the view which this factory will transmit messages to
     * @param s the scanner which this factory will read input from
     * @throws IllegalArgumentException if either parameter is null
     */
    public LumaFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    /**
     * Creates a new {@link LumaCommand} that takes in an image that has a name matching the first argument and saves
     * the resulting image to the model with a name using the second argument.
     * @param s1 the name of the image to perform the luma operation on
     * @param s2 the name of the resulting image
     * @return
     */
    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new LumaCommand(s1.get(), s2.get()));
    }
}
