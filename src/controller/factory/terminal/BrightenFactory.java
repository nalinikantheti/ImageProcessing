package controller.factory.terminal;

import command.BrightenCommand;
import command.Command;
import command.DarkenCommand;
import util.ImageProcessorUtils;
import view.ImageProcessorView;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static util.ImageProcessorUtils.ensureNotNull;

public class BrightenFactory extends AbstractShadeChangeFactory {

    public BrightenFactory(ImageProcessorView view, Scanner s) {
        super(view, s);
    }

    @Override
    protected Optional<Command> makeCommand(Optional<String> old, Optional<Integer> intensity, Optional<String> newName) {
        return Optional.of(new BrightenCommand(old.get(), intensity.get(), newName.get()));
    }


}
