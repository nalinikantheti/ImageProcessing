package command;

import javax.swing.*;

import model.ImageProcessorModel;
import view.ImageProcessorGUI;

public class SaveAsGuiCommand implements Command {
  ImageProcessorGUI gui;

  public SaveAsGuiCommand(ImageProcessorGUI gui) {
    this.gui = gui;
  }

  @Override
  public void run(ImageProcessorModel model) {
    JFileChooser file = new JFileChooser();
    int returnVal = file.showSaveDialog(null);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      String filepath = file.getSelectedFile().getAbsolutePath();
      String filetype = filepath.substring(filepath.lastIndexOf(".") + 1);
      if (filetype.equalsIgnoreCase("ppm")) {
        new SavePPMCommand(filepath, "working").run(model);
      } else {
        new SaveImageIOCommand(filepath, "working", filetype).run(model);
      }
    }
  }
}
