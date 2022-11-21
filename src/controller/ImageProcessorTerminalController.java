package controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import command.Command;
import controller.factory.CommandFactory;
import model.ImageProcessorModel;
import util.ImageProcessorUtils;
import view.ImageProcessorView;

/**
 * An ImageProcessorController that handles interactions between
 * inputs from terminal commands and a given model. Also handles
 * errors thrown due to bad inputs.
 */
public class ImageProcessorTerminalController implements ImageProcessorController {
  private Map<String, CommandFactory> commandFactories;

  private ImageProcessorModel model;
  private ImageProcessorView view;
  private Scanner scan;

  /**
   * A Constructor for an ImageProcessorTerminalController
   * that takes in three arguments and ensures they are not Null.
   *
   * @param model the model this controller will call based on the user input.
   * @param view  the view the controller will transmit to.
   * @param scan  the scanner used to read inputs from the user.
   */
  public ImageProcessorTerminalController(ImageProcessorModel model,
                                          ImageProcessorView view, Scanner scan) {
    ImageProcessorUtils.ensureNotNull(model, "model cannot be null.");
    ImageProcessorUtils.ensureNotNull(view, "view cannot be null.");
    ImageProcessorUtils.ensureNotNull(scan, "scanner cannot be null.");
    this.model = model;
    this.view = view;
    this.scan = scan;
    commandFactories = new HashMap<>();
  }

  /**
   * Runs the ImageProcessorProgram using terminal user inputs.
   * This program allows the user to run any of the commands, quit the program,
   * and provides feedback when a command has successfully been executed.
   * It also catches exceptions so the program will not terminate if an
   * exception is thrown.
   */
  @Override
  public void runProgram() {
    transmit("Welcome to Image Processor! Enter a command to begin:");
    while (scan.hasNext()) {
      String input = scan.next();
      if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
        transmit("Quitting program... ");
        return;
      }
      CommandFactory cmdfac = commandFactories.getOrDefault(input, null);
      if (cmdfac == null) {
        transmit("unknown command");
      } else {
        Optional<Command> c = cmdfac.make();
        if (c.isPresent()) {
          try {
            c.get().run(model);
            transmit("Successfully ran command!");
          } catch (IllegalArgumentException e) {
            transmit(e.getMessage());
          }
        } else {
          if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")) {
            transmit("Quitting program... ");
            return;
          }
        }
      }
    }
  }

  /**
   * Adds a new CommandFactory to this Hashmap<\String, CommandFactory> and ensures
   * that the given String and CommandFactory are not null.
   *
   * @param name Name of new Command to be added.
   * @param cmd  Command Factory for new Command to be added.
   */
  public void registerCommand(String name, CommandFactory cmd) {
    ImageProcessorUtils.ensureNotNull(name, "name cannot be null.");
    ImageProcessorUtils.ensureNotNull(cmd, "Command Factory cannot be null.");
    if (name.equalsIgnoreCase("q") || name.equalsIgnoreCase("quit")) {
      throw new IllegalArgumentException("Command name cannot be 'q' or 'quit'.");
    }
    this.commandFactories.put(name, cmd);
  }

  /**
   * Transmits a message by using this view's renderMessage method.
   *
   * @param message the message to be transmitted.
   * @throws IllegalStateException if writing to view via appendable fails.
   */
  public void transmit(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("unable to transmit to view.");
    }
  }


}
