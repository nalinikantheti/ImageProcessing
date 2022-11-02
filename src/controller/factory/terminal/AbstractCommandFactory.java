package controller.factory.terminal;

import command.Command;
import view.ImageProcessorView;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static util.ImageProcessorUtils.ensureNotNull;

public abstract class AbstractCommandFactory implements CommandFactory {
    private ImageProcessorView view;
    private Scanner s;

    public AbstractCommandFactory(ImageProcessorView view, Scanner s) {
        ensureNotNull(view, "View cannot be null.");
        ensureNotNull(s, "Scanner cannot be null.");
        this.view = view;
        this.s = s;
    }

    public Optional<Command> make(){
            Optional<String> imageName = waitForString(true);
            Optional<String> newName = waitForString(imageName.isPresent());
            if(imageName.isEmpty() || newName.isEmpty()) {
                return Optional.empty();
            }
            return makeCommand(imageName, newName);

    }

    protected Optional<String> waitForString(boolean hadPreviousValue) {
        if (s.hasNext() && hadPreviousValue) {
            String next = s.next();
            if (isQuit(next)) {
                return quitSequence();
            }
            return Optional.of(next);
        }
        return endOfInputSequence(hadPreviousValue);
    }

    protected Optional<Integer> waitForInteger(boolean hadPreviousValue, String message) {
        while (hadPreviousValue && s.hasNext()) {
            if (s.hasNextInt()) {
                return Optional.of(s.nextInt());
            } else {
                String next = s.next();
                if (isQuit(next)) {
                    return quitSequence();
                }
                transmit(message + ", got: " + next);
                transmit("Re-enter value.");
            }
        }
        return endOfInputSequence(hadPreviousValue);
    }

    private void transmit(String message) {
        try {
            view.renderMessage(message + "\n");
        } catch (IOException e) {
            throw new IllegalStateException("Could not transmit to view.");
        }
    }

    private boolean isQuit(String message) {
        return message.equalsIgnoreCase("q") || message.equalsIgnoreCase("quit");
    }

    private <T> Optional<T> quitSequence() {
        transmit("Quitting program...");
        return Optional.empty();
    }

    private <T> Optional<T> endOfInputSequence(boolean hadPreviousValue) {
        if(hadPreviousValue) {
            transmit("Reached end of input.");
        }
        return Optional.empty();
    }

    protected abstract Optional<Command> makeCommand(Optional<String> old, Optional<String> newName);
}
