package command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import image.Image;
import image.Pixel;
import model.ImageProcessorModel;

/**
 * A command that saves a given image as a PPM to a filesystem using a given filepath.
 */
public class SavePPMCommand implements Command {
  private String filepath;
  private String name;

  /**
   * A constructor for a SavePPMCommand that uses two arguments.
   *
   * @param filepath the filepath the image is being saved to.
   * @param name     the name of the image being saved.
   */
  public SavePPMCommand(String filepath, String name) {
    this.filepath = filepath;
    this.name = name;
  }

  /**
   * Runs this SavePPMCommand by calling the save method using a given model.
   *
   * @param model model used to save image to given filepath.
   */
  @Override
  public void run(ImageProcessorModel model) {
    try {
      this.savePPMToFileSystem(name, filepath, model);
    } catch (IOException e) {
      throw new IllegalArgumentException("file not found");
    }
  }


  /**
   * Saves the image as a PPM with the given name to the filesystem.
   * Retrieves an Image using a given name, constructs a portable pixel map
   * from it using a StringBuilder, and then saves it to a given filepath.
   *
   * @param name     name of image to be saved.
   * @param filepath location of image to be saved to.
   * @throws IOException if writing to file fails.
   */

  public void savePPMToFileSystem(String name, String filepath, ImageProcessorModel model)
          throws IOException {
    Image image = model.getImage(name);
    StringBuilder ppm = new StringBuilder();
    ppm.append("P3\n");
    ppm.append(image.getWidth() + " " + image.getHeight() + "\n");
    ppm.append("255\n");
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        Pixel pix = image.getPixel(x, y);
        ppm.append(pix.getRed() + "\n");
        ppm.append(pix.getGreen() + "\n");
        ppm.append(pix.getBlue() + "\n");
      }
    }

    Path file = Paths.get(filepath);
    Files.writeString(file, ppm);

  }
}

