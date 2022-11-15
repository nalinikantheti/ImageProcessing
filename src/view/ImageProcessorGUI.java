package view;

import java.awt.event.ActionListener;

public interface ImageProcessorGUI {

  public void display(String imageName);

  public void setListener(ActionListener listener);

  public void makeErrorPopUp(String message);

}
