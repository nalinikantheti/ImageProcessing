package command;

import image.Image;
import image.Pixel;
import model.ImageProcessorModel;
import util.ImageProcessorUtils;

/**
 * A command that processes an image into some sort of greyscale version of itself,
 * using either luma, intensity, value, red, green, or blue values to do so.
 * This command saves the greyscale version as a new file.
 */
public abstract class AbstractGreyScaleCommand implements Command {
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
    this.imageName = imageName;
    this.newName = newName;
  }

  /**
   * Runs this GreyScaleCommand by retrieving an image using the given model, iterating through
   * each pixel in the image, and using a value function to determine the new value
   * for all three rgb values in the pixel.
   *
   * @param model model used to retrieve image using given imageName.
   */
  @Override
  public void run(ImageProcessorModel model) {
    Image image = model.getImage(imageName);
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel old = image.getPixel(x, y);
        Pixel newPix = ImageProcessorUtils.createValidPixel(getValue(old),
                getValue(old),
                getValue(old));
        image.setPixel(newPix, x, y);
      }
    }

    model.saveImageToModel(image, newName);
  }

  /**
   * Gets a value to use for each pixel in the greyscale image, as such, returned values will become
   * the red, green, and blue values for each pixel. The value returned varies depending on the
   * subclass.
   * @param pixel the pixel that is used to determine the value
   * @return a value representing the RGB values of a greyscale pixel
   */
  protected abstract int getValue(Pixel pixel);
}

