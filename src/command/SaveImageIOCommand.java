package command;

import image.BufferWrapper;
import image.Image;
import model.ImageProcessorModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveImageIOCommand implements Command {
  private String filepath;
  private String name;
  private String filetype;

  public SaveImageIOCommand(String filepath, String name, String filetype) {
    this.filepath = filepath;
    this.name = name;
    this.filetype = filetype;
  }

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
