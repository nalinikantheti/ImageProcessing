package command;

import image.Image;
import image.Pixel;
import model.ImageProcessorModel;
import util.ImageProcessorUtils;

/**
 * A command that decreases each RGB value of each pixel
 * in an Image by a given value.
 */
public class DarkenCommand implements Command {
  private String imageName;
  private int intensity;
  private String newName;

  /**
   * Constructor for a DarkenCommand with three arguments.
   *
   * @param imageName name of image being darkened.
   * @param intensity value by which image is darkened.
   * @param newName   name that new darkened image is being saved to.
   */
  public DarkenCommand(String imageName, int intensity, String newName) {
    this.imageName = imageName;
    this.intensity = intensity;
    this.newName = newName;
  }

  /**
   * runs this DarkenCommand by retrieving an image, iterating through each pixel,
   * and decreasing each RGB value by the given intensity.
   *
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    Image image = model.getImage(imageName);
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel old = image.getPixel(x, y);
        Pixel newPix = ImageProcessorUtils.createValidPixel(old.getRed() - intensity,
                old.getGreen() - intensity,
                old.getBlue() - intensity);
        image.setPixel(newPix, x, y);
      }
    }

    model.saveImageToModel(image, newName);
  }
}
