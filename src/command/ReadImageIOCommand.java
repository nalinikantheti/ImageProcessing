package command;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import image.BufferWrapper;
import model.ImageProcessorModel;

public class ReadImageIOCommand implements Command{
  private String filepath;
  private String name;

  public ReadImageIOCommand(String filepath, String name) {
    this.filepath = filepath;
    this.name = name;
  }

  @Override
  public void run(ImageProcessorModel model) {
    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(filepath));
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

    model.saveImageToModel(new BufferWrapper(image), name);
  }
}
