package controller.factory.terminal;

import command.Command;
import command.GreyScaleGreenCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class GreyScaleGreenFactory extends AbstractCommandFactory{
    public GreyScaleGreenFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> old, Optional<String> newName) {
        return Optional.of(new GreyScaleGreenCommand(old.get(), newName.get()));
    }
}
