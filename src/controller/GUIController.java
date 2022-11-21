package controller;

import java.util.HashMap;
import java.util.Optional;

import command.Command;
import controller.factory.CommandFactory;
import model.ImageProcessorModel;
import util.ImageProcessorUtils;
import view.ImageProcessorGUI;

/**
 * An ImageProcessorGUIController that handles interactions between a gui
 * and a given model. Also handles errors thrown by creating error popup
 * windows.
 **/
public class GUIController implements Listener {
  private ImageProcessorModel model;
  private ImageProcessorGUI gui;
  private HashMap<String, CommandFactory> commandFactories;

  /**
   * A constructor for a GUIController that takes in a model and a gui,
   * and ensures they are not null.
   *
   * @param model the model this controller will use.
   * @param gui   the gui this controller will use.
   */
  public GUIController(ImageProcessorModel model,
                       ImageProcessorGUI gui) {
    ImageProcessorUtils.ensureNotNull(model, "Model cannot be null.");
    ImageProcessorUtils.ensureNotNull(gui, "GUI view cannot be null.");
    this.commandFactories = new HashMap<String, CommandFactory>();
    this.model = model;
    this.gui = gui;
  }

  /**
   * Adds a given command factory to this map of command factories.
   *
   * @param name name of command factory to be registered.
   * @param cf   the command factory being registered.
   */
  public void registerFactory(String name, CommandFactory cf) {
    ImageProcessorUtils.ensureNotNull(cf, "Command Factory cannot be null.");
    commandFactories.put(name, cf);
  }

  /**
   * Runs the command that correlates with the given actionCommand.
   * Creates an error popup if any part of running the command fails.
   *
   * @param actionCommand the actionCommand to be run.
   */
  public void actionPerformed(String actionCommand) {
    if (commandFactories.containsKey(actionCommand)) {
      Optional<Command> cmd = commandFactories.get(actionCommand).make();
      if (cmd.isPresent()) {
        try {
          cmd.get().run(model);
        } catch (IllegalArgumentException e) {

          gui.makeErrorPopUp(e.getMessage());
        }
        gui.display("working");
      } else {
        gui.makeErrorPopUp("Command failed to run.");
      }
    } else {
      gui.makeErrorPopUp("Command: " + actionCommand + " not found.");
    }
  }
}
