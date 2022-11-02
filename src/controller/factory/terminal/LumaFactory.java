package controller.factory.terminal;

import command.Command;
import command.LumaCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class LumaFactory extends AbstractCommandFactory{
    public LumaFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> old, Optional<String> newName) {
        return Optional.of(new LumaCommand(old.get(), newName.get()));
    }
}
