package command;

import model.ImageProcessorModel;
import view.ImageProcessorGUI;

public class UpdateViewCommand implements Command{


  private ImageProcessorGUI view;
  private Command command;
  private String imageName;

  public UpdateViewCommand(ImageProcessorGUI view, Command command, String imageName) {
    this.view = view;
    this.command = command;
    this.imageName = imageName;
  }

  @Override
  public void run(ImageProcessorModel model) {
    this.command.run(model);
    this.view.display(imageName);
  }
}
