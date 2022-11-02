package controller.factory.terminal;

import command.Command;
import command.LoadCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class LoadFactory extends AbstractTwoStringFactory{
    public LoadFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2) {
        return Optional.of(new LoadCommand(s1.get(), s2.get()));
    }
}
