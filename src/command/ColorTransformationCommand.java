package command;

import image.Image;
import image.Pixel;
import model.ImageProcessorModel;

import static util.ImageProcessorUtils.createValidPixel;

/**
 * A Command that applies an operation to each RGB value of each pixel in an Image.
 */
public abstract class ColorTransformationCommand implements Command {
  protected String imageName;
  protected String newName;

  /**
   * A constructor for a ColorTransformationCommand with two arguments.
   *
   * @param imageName The name of the image to be transformed.
   * @param newName   the name the transformed image will be saved to.
   */
  public ColorTransformationCommand(String imageName, String newName) {
    this.imageName = imageName;
    this.newName = newName;
  }

  /**
   * Runs this filter command by retrieving an image with a given model, iterating through each
   * pixel in the image, and applying a red operation on the red value, a green operation on the
   * green value, and a blue operation on the blue value.
   *
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    Image image = model.getImage(imageName);
    for (int x = 0; x < image.getWidth(); x += 1) {
      for (int y = 0; y < image.getHeight(); y += 1) {
        Pixel pixel = image.getPixel(x, y);
        image.setPixel(createValidPixel(getRed(pixel), getGreen(pixel),
                getBlue(pixel)), x, y);
      }
    }
    model.saveImageToModel(image, newName);
  }

  /**
   * performs an operation on the blue value of a given pixel.
   *
   * @param pixel the pixel whose blue value will be transformed.
   * @return the new blue value as an int for the given pixel.
   */
  protected abstract int getBlue(Pixel pixel);

  /**
   * performs an operation on the green value of a given pixel.
   *
   * @param pixel the pixel whose green value will be transformed.
   * @return the new green value as an int for the given pixel.
   */
  protected abstract int getGreen(Pixel pixel);

  /**
   * performs an operation on the red value of a given pixel.
   *
   * @param pixel the pixel whose red value will be transformed.
   * @return the new red value as an int for the given pixel.
   */
  protected abstract int getRed(Pixel pixel);
}
