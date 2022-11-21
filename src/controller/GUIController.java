package controller;
import java.util.HashMap;
import java.util.Optional;

import command.Command;
import controller.factory.CommandFactory;
import model.ImageProcessorModel;
import util.ImageProcessorUtils;
import view.ImageProcessorGUI;

public class GUIController implements Listener {
  private ImageProcessorModel model;
  private ImageProcessorGUI gui;
  private HashMap<String, CommandFactory> commandFactories;

  public GUIController (ImageProcessorModel model,
                       ImageProcessorGUI gui) {
    ImageProcessorUtils.ensureNotNull(model, "Model cannot be null.");
    ImageProcessorUtils.ensureNotNull(gui, "GUI view cannot be null.");
    this.commandFactories = new HashMap<String, CommandFactory>();
    this.model = model;
    this.gui = gui;
  }

  public void registerFactory(String name, CommandFactory cf){
    ImageProcessorUtils.ensureNotNull(cf, "Command Factory cannot be null.");
    commandFactories.put(name, cf);
  }

  public void actionPerformed(String actionCommand) {
    if(commandFactories.containsKey(actionCommand)){
      Optional<Command> cmd = commandFactories.get(actionCommand).make();
      if(cmd.isPresent()) {
        try{
          cmd.get().run(model);
        } catch(IllegalArgumentException e){

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
