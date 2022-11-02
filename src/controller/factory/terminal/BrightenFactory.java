package controller.factory.terminal;

import command.BrightenCommand;
import command.Command;
import util.ImageProcessorUtils;
import view.ImageProcessorView;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static util.ImageProcessorUtils.ensureNotNull;

public class BrightenFactory extends AbstractCommandFactory {

    public BrightenFactory(ImageProcessorView view, Scanner s) {
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
        return Optional.of(new BrightenCommand(imageName.get(), intensity.get(), newName.get()));
    }

}
