package command;


import image.Image;
import image.Pixel;
import model.ImageProcessorModel;
import util.ImageProcessorUtils;

/**
 * A command that increases each RGB value of each pixel of an image by a given value.
 */
public class BrightenCommand implements Command {
  private String imageName;
  private int intensity;
  private String newName;

  /**
   * constructor for a BrightenCommand that takes in three arguments.
   *
   * @param imageName the name of the image being brightened.
   * @param intensity the value the image is being brightened by.
   * @param newName   the name the new brightened image is being saved as.
   */
  public BrightenCommand(String imageName, int intensity, String newName) {
    this.imageName = imageName;
    this.intensity = intensity;
    this.newName = newName;
  }

  /**
   * runs this BrightenCommand by retrieving an image, iterating through each pixel in the image.
   * and increasing each RGB value by the given intensity.
   *
   * @param model the model used to retrieve the image from the imageName.
   */
  @Override
  public void run(ImageProcessorModel model) {
    Image image = model.getImage(imageName);
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel old = image.getPixel(x, y);
        Pixel newPix = ImageProcessorUtils.createValidPixel(old.getRed() + intensity,
                old.getGreen() + intensity,
                old.getBlue() + intensity);
        image.setPixel(newPix, x, y);
      }
    }

    model.saveImageToModel(image, newName);
  }


}

