package controller.factory.terminal;

import command.Command;
import view.ImageProcessorView;

import java.util.Optional;
import java.util.Scanner;

public interface CommandFactory {
    Optional<Command> make();
}
