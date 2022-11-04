package controller.factory.terminal;

import command.Command;

import java.util.Optional;

/**
 * Acts as a mediator between terminal input and command creation. Essentially, a CommandFactory handles the control
 * flow for converting text arguments into command arguments, asking the user to re-enter invalid arguments and
 * quitting the program if necessary. Exact implementations may differ.
 */
public interface CommandFactory {
    /**
     * If given the correct inputs, returns an {@code Optional<Command>} containing a runnable command. If the user
     * quits the program mid-execution of this method, returns an empty {@code Optional<>}.
     *
     * @return an optional Command, see above for details
     */
    Optional<Command> make();
}
