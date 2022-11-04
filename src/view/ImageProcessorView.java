package view;

import java.io.IOException;

/**
 * Represents a view for the ImageProcessing program.
 */
public interface ImageProcessorView {
  /**
   * Renders the given message to the view.
   *
   * @param message the message to render
   * @throws IOException if the message cannot be rendered
   */
  public void renderMessage(String message) throws IOException;
}
