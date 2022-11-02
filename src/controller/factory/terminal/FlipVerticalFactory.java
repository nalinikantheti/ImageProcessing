package controller.factory.terminal;

import command.Command;
import command.FlipVerticalCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class FlipVerticalFactory extends AbstractCommandFactory{
    public FlipVerticalFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> old, Optional<String> newName) {
        return Optional.of(new FlipVerticalCommand(old.get(), newName.get()));
    }
}
