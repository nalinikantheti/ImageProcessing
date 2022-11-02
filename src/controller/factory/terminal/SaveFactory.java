package controller.factory.terminal;

import command.Command;
import command.SaveCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class SaveFactory extends AbstractTwoStringFactory{
    public SaveFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new SaveCommand(s1.get(), s2.get()));
    }
}
