package controller.factory.terminal;

import command.Command;
import command.IntensityCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class IntensityFactory extends AbstractCommandFactory{
    public IntensityFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> old, Optional<String> newName) {
        return Optional.of(new IntensityCommand(old.get(), newName.get()));
    }
}
