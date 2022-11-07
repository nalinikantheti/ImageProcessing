package command;

import java.io.FileNotFoundException;

import image.Image;
import model.ImageProcessorModel;
import util.ImageProcessorUtils;
import util.ImageUtil;

/**
 * A command that loads an image from a given filepath and saves it to a given string.
 */
public class LoadPPMCommand implements Command {
  private String filepath;
  private String newName;

  /**
   * A Constructor for a LoadCommand with two arguments.
   *
   * @param filepath the filepath the image will be loaded from.
   * @param newName  the name by which the loaded image will be saved as.
   */
  public LoadPPMCommand(String filepath, String newName) {
    this.filepath = filepath;
    this.newName = newName;
  }


  /**
   * Loads an image from the filesystem into the model with the given name.
   * Loads image of given name from given filepath.
   *
   * @param model model used to retrieve image from filepath.
   */
  @Override
  public void run(ImageProcessorModel model) {
    try {
      Image loadImage = ImageUtil.readPPM(filepath);
      model.saveImageToModel(loadImage, newName);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file not found");
    }
  }

}
