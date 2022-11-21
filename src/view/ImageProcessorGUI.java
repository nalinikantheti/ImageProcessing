package view;

import java.io.IOException;

import controller.Listener;

public interface ImageProcessorGUI {

  public void display(String imageName);

  public void setListener(Listener listener);

  public void makeErrorPopUp(String message);

}
