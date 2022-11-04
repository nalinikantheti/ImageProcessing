package command;

import image.Pixel;

/**
 * A command that greyscales an image by setting each
 * rgb value in a pixel to the average of all three.
 */
public class IntensityCommand extends AbstractGreyScaleCommand {

  /**
   * A constructor for an IntensityCommand using two arguments.
   *
   * @param imageName the name of the image to be processed.
   * @param newName   the name the processed image will be saved as.
   */
  public IntensityCommand(String imageName, String newName) {
    super(imageName, newName);
  }

  /**
   * Returns the intensity of the given pixel, which is used in the greyscale image.
   * @param pixel the pixel that is used to determine the value
   * @return the intensity of the given pixel
   */
  @Override
  protected int getValue(Pixel pixel) {
    return (int) ((pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3.0);
  }
}
