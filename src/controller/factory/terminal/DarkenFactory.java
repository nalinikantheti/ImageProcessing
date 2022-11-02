package controller.factory.terminal;

import command.BrightenCommand;
import command.Command;
import command.DarkenCommand;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public class DarkenFactory extends AbstractCommandFactory{
    public DarkenFactory(ImageProcessorView view, Scanner s) {
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
        return Optional.of(new DarkenCommand(imageName.get(), intensity.get(), newName.get()));
    }


}
