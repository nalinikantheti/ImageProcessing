package controller.factory.terminal;

import command.Command;
import command.FlipVerticalCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class FlipVerticalFactory extends AbstractTwoStringFactory {
    public FlipVerticalFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new FlipVerticalCommand(s1.get(), s2.get()));
    }
}
