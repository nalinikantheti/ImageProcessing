package controller.factory.terminal;

import command.Command;
import command.FlipHorizontalCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class FlipHorizontalFactory extends AbstractTwoStringFactory {
    public FlipHorizontalFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new FlipHorizontalCommand(s1.get(), s2.get()));
    }

}
