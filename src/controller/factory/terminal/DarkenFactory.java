package controller.factory.terminal;

import command.BrightenCommand;
import command.Command;
import command.DarkenCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class DarkenFactory extends AbstractShadeChangeFactory{
    public DarkenFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand
            (Optional<String> old, Optional<Integer> intensity, Optional<String> newName) {
        return Optional.of(new DarkenCommand(old.get(), intensity.get(), newName.get()));
    }


}
