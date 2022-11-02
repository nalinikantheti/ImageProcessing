package controller.factory.terminal;

import command.Command;
import command.ValueCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class ValueFactory extends AbstractCommandFactory{
    public ValueFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> old, Optional<String> newName) {
        return Optional.of(new ValueCommand(old.get(), newName.get()));
    }
}
