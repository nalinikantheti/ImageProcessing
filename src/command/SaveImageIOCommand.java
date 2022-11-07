package command;

import image.BufferWrapper;
import image.Image;
import model.ImageProcessorModel;

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


  }
}
