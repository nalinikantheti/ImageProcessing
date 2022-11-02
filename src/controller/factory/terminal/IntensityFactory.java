package controller.factory.terminal;

import command.Command;
import command.IntensityCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class IntensityFactory extends AbstractTwoStringFactory {
    public IntensityFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new IntensityCommand(s1.get(), s2.get()));
    }
}
