package view;

import controller.factory.terminal.CommandFactory;

public interface ImageProcessorGUI {

  public void display(String imageName);

  public void registerCommandFactory(CommandFactory cmdf);

  public void makeErrorPopUp(String message);

}
