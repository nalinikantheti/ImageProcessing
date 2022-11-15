package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import controller.factory.CommandFactory;
import model.ImageProcessorModel;
import view.ImageProcessorGUI;

public class GUIController implements ActionListener {
  ImageProcessorModel model;

  ImageProcessorGUI gui;
  HashMap<String, CommandFactory> commandFactories;

  public GUIController (ImageProcessorModel model,
                       ImageProcessorGUI gui) {
    this.commandFactories = new HashMap<String, CommandFactory>();
    this.model = model;
    this.gui = gui;
  }

  public void registerFactory(String name, CommandFactory cf){
    commandFactories.put(name, cf);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    commandFactories.get(e.getActionCommand()).make().get().run(model);
    gui.display("working");
  }

}
