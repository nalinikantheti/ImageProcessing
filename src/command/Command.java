package command;

import model.ImageProcessorModel;

/**
 * A function object that represents different ways Images can be processed.
 * ex: brighten, darken, flipHorizontal all implement this interface.
 */
public interface Command {
  /**
   * runs this command on an image using a model to retrieve the image.
   *
   * @param model model used to retrieve image for command to then process.
   */
  public void run(ImageProcessorModel model);
}
