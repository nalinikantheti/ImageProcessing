package view;

import controller.Listener;

/**
 * Represents a Graphical User Interface for an Image Processor.
 */
public interface ImageProcessorGUI {

  /**
   * updates the image in the processor with the given image.
   * @param imageName the name of the image to be displayed.
   */
  void display(String imageName);

  /**
   * Sets the listener for this gui to the given listener.
   *
   * @param listener the listener to be set.
   */
  void setListener(Listener listener);

  /**
   * Creates an error popup window with the given message.
   *
   * @param message the message to be displayed.
   */
  void makeErrorPopUp(String message);

}
