package controller.factory.terminal;

import command.BrightenCommand;
import command.Command;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public abstract class AbstractShadeChangeFactory extends AbstractCommandFactory{
    public AbstractShadeChangeFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }
    @Override
    public Optional<Command> make() {
        Optional<String> imageName = waitForString(true);
        Optional<Integer> intensity = waitForInteger(imageName.isPresent(), "Expected integer for intensity") ;
        Optional<String> newName = waitForString(intensity.isPresent());
        if(imageName.isEmpty() || intensity.isEmpty() || newName.isEmpty()) {
            return Optional.empty();
        }
        return makeCommand(imageName, intensity, newName);
    }

    protected abstract Optional<Command> makeCommand
            (Optional<String> old, Optional<Integer> intensity, Optional<String> newName);

}
