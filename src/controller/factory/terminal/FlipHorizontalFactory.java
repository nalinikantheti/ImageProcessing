package controller.factory.terminal;

import command.Command;
import command.FlipHorizontalCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class FlipHorizontalFactory extends AbstractCommandFactory{
    public FlipHorizontalFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> old, Optional<String> newName) {
        return Optional.of(new FlipHorizontalCommand(old.get(), newName.get()));
    }

}
