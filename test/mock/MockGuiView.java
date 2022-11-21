package mock;

import java.io.IOException;

import controller.Listener;
import view.ImageProcessorGUI;

/**
 * A mock GUI view which logs all the operations performed on this view.
 */
public class MockGuiView implements ImageProcessorGUI {
  Appendable log;

  /**
   * Constructor for {@link MockGuiView}.
   *
   * @param log the log that will track all operations.
   */
  public MockGuiView(Appendable log) {
    this.log = log;
  }

  /**
   * logs that the image has been updated.
   *
   * @param imageName the name of the new image to be displayed.
   */
  @Override
  public void display(String imageName) {
    try {
      log.append("Updated image with " + imageName);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }

  }

  /**
   * logs that the listener has been set.
   *
   * @param listener listener to be added to this guiview.
   */
  @Override
  public void setListener(Listener listener) {
    try {
      log.append("Set listener.");
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }

  }

  /**
   * logs that an error popup was created with the given message.
   *
   * @param message the error message to be displayed.
   */
  @Override
  public void makeErrorPopUp(String message) {
    try {
      log.append("Made error popup: " + message);
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }

  }
}
