package command;

import image.BufferWrapper;
import image.Image;
import model.ImageProcessorModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * A command that saves an Image of a specified filetype to a given filepath.
 */
public class SaveImageIOCommand implements Command {
  private String filepath;
  private String name;
  private String filetype;

  /**
   * A Constructor for a SavePPMCommand that takes in three arguments.
   * @param filepath the filepath the image will be saved to.
   * @param name the name of the image being saved.
   * @param filetype the type the image will be saved as.
   */
  public SaveImageIOCommand(String filepath, String name, String filetype) {
    this.filepath = filepath;
    this.name = name;
    this.filetype = filetype;
  }

  /**
   * Runs this SaveImageIOCommand by using a model to retrieve an image,
   * create a bufferedImage from the retrieved image, and then use the
   * ImageIO writer to save that buffered image as the given filetype to
   * the given filepath.
   * @param model model used to retrieve image for command to then process.
   */
  @Override
  public void run(ImageProcessorModel model) {
    Image image = model.getImage(name);
    BufferedImage buffer = new BufferedImage(image.getWidth(), image.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    BufferWrapper wrapper = new BufferWrapper(buffer);
    for(int x = 0; x < image.getWidth(); x += 1) {
      for(int y = 0; y < image.getHeight(); y += 1) {
        wrapper.setPixel(image.getPixel(x,y),x,y);
      }
    }
    try {
      if(!ImageIO.write(buffer,filetype,new File(filepath))) {
        throw new IllegalArgumentException("Could not write image as " + filetype);
      }
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }
}
