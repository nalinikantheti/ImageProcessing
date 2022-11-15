package command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import image.BufferWrapper;
import model.ImageProcessorModel;
import view.ImageProcessorGUI;

import static util.ImageProcessorUtils.ensureNotNull;

/**
 * A Command that will read (or load) an image of any type using ImageIO.
 */
public class ReadImageIOCommand implements Command {
  private String filepath;
  private String name;

  /**
   * A Constructor for a ReadImageIOCommand using two arguments.
   *
   * @param filepath the filepath of the image to be read.
   * @param name     the name the image will be saved as in the model.
   */
  public ReadImageIOCommand(String filepath, String name) {
    this.filepath = filepath;
    this.name = name;
  }

  /**
   * Runs this ReadImageIOCommand by retrieving a bufferedImage from the given
   * filepath using ImageIO.read, and then wrapping it in a bufferWrapper, and
   * saving the wrapped image to the given model.
   *
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(filepath));
      ensureNotNull(image, "Could not read image.");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

    model.saveImageToModel(new BufferWrapper(image), name);
  }
}
