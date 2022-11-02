package controller.factory.terminal;

import command.Command;
import command.GreyScaleBlueCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class GreyScaleBlueFactory extends AbstractCommandFactory{
    public GreyScaleBlueFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> old, Optional<String> newName) {
        return Optional.of(new GreyScaleBlueCommand(old.get(), newName.get()));
    }
}
