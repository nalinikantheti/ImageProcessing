package controller.factory.terminal;

import command.Command;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public abstract class AbstractTwoStringFactory extends AbstractCommandFactory{
    public AbstractTwoStringFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    public Optional<Command> make(){
        Optional<String> imageName = waitForString(true);
        Optional<String> newName = waitForString(imageName.isPresent());
        if(imageName.isEmpty() || newName.isEmpty()) {
            return Optional.empty();
        }
        return makeCommand(imageName, newName);

    }
    protected abstract Optional<Command> makeCommand(Optional<String> s1, Optional<String> s2);
}
