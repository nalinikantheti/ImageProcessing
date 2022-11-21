package mock;

import java.io.IOException;

import controller.Listener;
import view.ImageProcessorGUI;

public class MockGuiView implements ImageProcessorGUI {
  Appendable log;

  public MockGuiView(Appendable log) {
    this.log = log;
  }
  @Override
  public void display(String imageName) {
    try{
      log.append("Updated image with " + imageName);
    } catch(IOException e){
      throw new IllegalStateException(e.getMessage());
    }

  }

  @Override
  public void setListener(Listener listener) {
    try{
      log.append("Set listener.");
    } catch(IOException e){
      throw new IllegalStateException(e.getMessage());
    }

  }

  @Override
  public void makeErrorPopUp(String message) {
    try{
      log.append("Made error popup: " + message);
    } catch(IOException e){
      throw new IllegalStateException(e.getMessage());
    }

  }
}
