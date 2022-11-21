package command;

import javax.swing.*;

import model.ImageProcessorModel;
import view.ImageProcessorGUI;

public class OpenGuiCommand implements Command {
  ImageProcessorGUI gui;

  public OpenGuiCommand(ImageProcessorGUI gui) {
    this.gui = gui;
  }

  @Override
  public void run(ImageProcessorModel model) {
    JFileChooser file = new JFileChooser();
    int returnVal = file.showOpenDialog(null);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      String filepath = file.getSelectedFile().getAbsolutePath();
      String filetype = filepath.substring(filepath.lastIndexOf(".") + 1);
      if (filetype.equalsIgnoreCase("ppm")) {
        new LoadPPMCommand(filepath, "working").run(model);
      } else {
        new ReadImageIOCommand(filepath, "working").run(model);
      }
    }
  }
}
