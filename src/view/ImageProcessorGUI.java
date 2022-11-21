package view;

import controller.Listener;

/**
 * Represents a Graphical User Interface for an Image Processor.
 */
public interface ImageProcessorGUI {

  /**
   * updates the image in the processor with the given image.
   *
   * @param imageName
   */
  void display(String imageName);

  /**
   * Sets the listener for this gui to the given listener.
   *
   * @param listener
   */
  void setListener(Listener listener);

  /**
   * Creates an error popup window with the given message.
   *
   * @param message
   */
  void makeErrorPopUp(String message);

}
