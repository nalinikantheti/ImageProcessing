package command;

import image.Pixel;

/**
 * A command that turns an image into greyscale by setting the
 * * RGB value of every pixel to its green value.
 */
public class GreyScaleGreenCommand extends AbstractGreyScaleCommand {
  /**
   * A constructor for a GreyScaleGreenCommand using two arguments.
   *
   * @param imageName the name of the image being processed.
   * @param newName   the name the processed image will be saved as.
   */
  public GreyScaleGreenCommand(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  protected int getValue(Pixel pixel) {
    return pixel.getGreen();
  }
}
