package command;

import image.Pixel;

/**
 * A command that processes an image into some sort of greyscale version of itself,
 * using either luma, intensity, value, red, green, or blue values to do so.
 * This command saves the greyscale version as a new file.
 */
public abstract class AbstractGreyScaleCommand extends ColorTransformationCommand {
  protected String imageName;
  protected String newName;

  /**
   * A constructor for an abstract grey scale command, which only takes in the name of the
   * image to be processed, and the new name to save the processed image to.
   *
   * @param imageName the name of the image to be processed
   * @param newName   the name the processed image is being saved as
   */
  protected AbstractGreyScaleCommand(String imageName, String newName) {
    super(imageName, newName);
  }

  /**
   * Gets a value to use for each pixel in the greyscale image, as such, returned values will become
   * the red, green, and blue values for each pixel. The value returned varies depending on the
   * subclass.
   *
   * @param pixel the pixel that is used to determine the value
   * @return a value representing the RGB values of a greyscale pixel
   */
  protected abstract int getValue(Pixel pixel);

  @Override
  protected int getRed(Pixel pixel) {
    return getValue(pixel);
  }

  @Override
  protected int getGreen(Pixel pixel) {
    return getValue(pixel);
  }

  @Override
  protected int getBlue(Pixel pixel) {
    return getValue(pixel);
  }
}

