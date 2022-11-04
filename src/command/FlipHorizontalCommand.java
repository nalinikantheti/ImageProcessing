package command;

import image.Image;
import model.ImageProcessorModel;

/**
 * A command that flips an image on its vertical axis and saves the
 * modified image as a different file.
 */
public class FlipHorizontalCommand implements Command {

  private String imageName;
  private String newName;

  /**
   * constructor for a FlipHorizontalCommand with two arguments.
   *
   * @param imageName the name of the image being processed.
   * @param newName   the name the processed image is saved to.
   */
  public FlipHorizontalCommand(String imageName, String newName) {
    this.imageName = imageName;
    this.newName = newName;
  }

  /**
   * runs this FlipHorizontalCommand on an image.
   *
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    Image oldImage = model.getImage(imageName);
    Image newImage = oldImage.clone();

    for (int y = 0; y < oldImage.getHeight(); y++) {
      for (int x = 0; x < oldImage.getWidth(); x++) {
        newImage.setPixel(oldImage.getPixel(oldImage.getWidth() - x - 1, y), x, y);
      }
    }

    model.saveImageToModel(newImage, newName);
  }
}
