package command;

import java.io.FileNotFoundException;

import image.Image;
import model.ImageProcessorModel;
import util.ImageUtil;

/**
 * A command that loads a PPM image from a given filepath and saves it to a given name.
 */
public class LoadPPMCommand implements Command {
  private String filepath;
  private String newName;

  /**
   * A Constructor for a LoadPPMCommand with two arguments.
   *
   * @param filepath the filepath the PPM image will be loaded from.
   * @param newName  the name by which the loaded image will be saved as.
   */
  public LoadPPMCommand(String filepath, String newName) {
    this.filepath = filepath;
    this.newName = newName;
  }


  /**
   * Loads a PPM image from the filesystem into the model with the given name.
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
