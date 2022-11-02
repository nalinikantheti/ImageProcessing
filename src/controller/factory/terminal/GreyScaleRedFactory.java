package controller.factory.terminal;

import command.Command;
import command.GreyScaleRedCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class GreyScaleRedFactory extends AbstractCommandFactory{
    public GreyScaleRedFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> old, Optional<String> newName) {
        return Optional.of(new GreyScaleRedCommand(old.get(), newName.get()));
    }
}
